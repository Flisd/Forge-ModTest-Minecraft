package net.agent.testmod.enchantment;

import net.agent.testmod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
public class StripMiningEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getMainHandItem();

        // Check if the tool has the Strip Mining enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.STRIP_MINING.get(), tool) > 0) {
            BlockState state = event.getState();
            BlockPos pos = event.getPos();
            ServerLevel world = (ServerLevel) event.getLevel();

            // Position one block below the mined block
            BlockPos blockBelowPos = pos.below();
            BlockState blockBelowState = world.getBlockState(blockBelowPos);
            Block blockBelow = blockBelowState.getBlock();

            // Check if the block below can be mined with a pickaxe
            if (blockBelowState.getBlock().defaultDestroyTime() > 0 && tool.isCorrectToolForDrops(blockBelowState)) {

                // Apply Silk Touch or Fortune
                if (EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.SILK_TOUCH, tool) > 0) {
                    // Use Silk Touch logic
                    Block.dropResources(blockBelowState, world, blockBelowPos, null, player, tool);
                } else {
                    // Use Fortune logic
                    int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.BLOCK_FORTUNE, tool);
                    Block.dropResources(blockBelowState, world, blockBelowPos, null, player, tool);
                }

                // Destroy the block below
                world.destroyBlock(blockBelowPos, false); // false to prevent default drops
            }

            // Random chance to grant Haste 3 effect
            Random random = new Random();
            if (random.nextInt(100) < 10) { // 10% chance to give Haste 3
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 60, 2)); // 60 ticks (3 seconds) of Haste 3
            }
        }
    }
}
