package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import org.jetbrains.annotations.Nullable;

public class FogBlock extends Block {
    public FogBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isAir(BlockState state) {
        return true; // Makes the block behave like air
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty(); // No collision shape, player can walk through
    }

    @Override
    public boolean canBeReplaced(BlockState p_60470_, BlockPlaceContext p_60471_) {
        return super.canBeReplaced(p_60470_, p_60471_);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        // Prevents the block from dropping as an item
    }
}
