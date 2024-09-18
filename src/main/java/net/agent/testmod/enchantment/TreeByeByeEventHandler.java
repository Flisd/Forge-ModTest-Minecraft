package net.agent.testmod.enchantment;

import net.agent.testmod.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class TreeByeByeEventHandler {

    private static final int MAX_BLOCKS = 120;

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getMainHandItem();

        // Check if the tool has the ShockWave enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.TREE_BYE.get(), tool) > 0) {
            if (!player.level().isClientSide) {
                BlockPos pos = event.getPos();
                ServerLevel world = (ServerLevel) event.getLevel();

                // Check and break adjacent wood blocks with a limit
                breakAdjacentWood(world, pos, player, tool, 0);
                player.level().playSound(player, player.blockPosition(), ModSounds.BYE_BYE_BYE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
                // Random chance to grant Haste 3 effect
                Random random = new Random();
                if (random.nextInt(100) < 10) { // 10% chance to give Haste 3
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 60, 2)); // 60 ticks (3 seconds) of Haste 3
                }
            }
        }
    }

    private static void breakAdjacentWood(ServerLevel world, BlockPos pos, Player player, ItemStack tool, int count) {
        if (count >= MAX_BLOCKS) {
            return;
        }

        BlockState state = world.getBlockState(pos);
        if (state.is(BlockTags.LOGS)) {
            Block.dropResources(state, world, pos, null, player, tool);
            world.destroyBlock(pos, false); // false to prevent default drops

            for (BlockPos adjacentPos : new BlockPos[]{pos.above(), pos.below(), pos.north(), pos.south(), pos.east(), pos.west()}) {
                if (world.getBlockState(adjacentPos).is(BlockTags.LOGS)) {
                    breakAdjacentWood(world, adjacentPos, player, tool, count + 1);
                }
            }
        }
    }
}