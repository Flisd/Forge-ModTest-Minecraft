package net.agent.testmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class BeaconSquareBlockEntity extends BlockEntity {
    public BeaconSquareBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BEACON_SQUARE.get(), pos, state);
    }

    @Override
    public AABB getRenderBoundingBox() {
        return new AABB(worldPosition).inflate(1.0D);
    }
}
