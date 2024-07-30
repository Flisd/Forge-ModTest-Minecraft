package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EnderBlock extends Block {
    boolean msgSent;
    private boolean teleported = false; // Add this line
    private int teleportDelay = 0;
    public EnderBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState p_152433_, Entity entity) {
        if (teleported) {
            return;
        }

        int radius = 25;
        BlockPos blockPos = null;

        // Iterate over a 50x50 area around the original block
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos currentPos = pos.offset(x, y, z);
                    BlockState currentState = world.getBlockState(currentPos);
                    if (currentState.getBlock() instanceof EnderBlock && !currentPos.equals(pos)) {
                        blockPos = currentPos;
                    }
                }
            }
        }
        if (blockPos == null && !msgSent) {
            entity.sendSystemMessage(Component.literal("No other ender block found in the given radius of: " + radius));
            msgSent = true;
            return;
        }
        entity.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
        teleported = true;
        teleportDelay = 20;
        super.stepOn(world, pos, p_152433_, entity);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (!world.isClientSide) {
            if (teleportDelay > 0) {
                teleportDelay--;
            } else {
                teleported = false;
            }
        }
        super.entityInside(state, world, pos, entity);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal("Stand to teleport"));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }
}
