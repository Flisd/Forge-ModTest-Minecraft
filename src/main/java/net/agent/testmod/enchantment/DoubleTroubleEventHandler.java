package net.agent.testmod.enchantment;

import net.agent.testmod.enchantment.ModEnchantments;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class DoubleTroubleEventHandler {

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getMainHandItem();

        // Check if the tool has the Double Trouble enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.DOUBLE_TROUBLE.get(), tool) > 0) {
            BlockState state = event.getState();
            Block block = state.getBlock();

            // Check if the block can be broken with a shovel (sand, gravel, etc.)
            if (block == Blocks.SAND || block == Blocks.GRAVEL) {
                Random random = new Random();
                int chance = random.nextInt(100);

                if (chance < 60) { // 60% chance to double the item drop
                    if (event.getLevel() instanceof ServerLevel) {
                        BlockPos pos = event.getPos();
                        Block.dropResources(state, (ServerLevel) event.getLevel(), pos);
                    }
                } else if (chance < 90) { // 30% chance for normal drop
                    // Default behavior, do nothing
                } else { // 10% chance to destroy the item (no drop)
                    event.setCanceled(true);
                }
            }
        }
    }
}
