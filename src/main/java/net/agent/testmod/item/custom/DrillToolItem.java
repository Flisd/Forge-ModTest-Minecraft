package net.agent.testmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


import java.util.List;

public class DrillToolItem extends Item {
    public DrillToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(1, miner, (p_220038_0_) -> {
                p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            // Check if the miner is a player
            if (miner instanceof Player) {
                Player player = (Player) miner;

                // Iterate over a 3x3 area around the original block
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos currentPos = pos.offset(x, y, z);
                            BlockState currentState = world.getBlockState(currentPos);
                            // Check if the current block can be mined with a pickaxe
                            if (currentState.getDestroySpeed(world, currentPos) != 0.0F) {
                                // Mine the block
                                world.destroyBlock(currentPos, true, player);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}