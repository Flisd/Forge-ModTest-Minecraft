package net.agent.testmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HologramBlockEntity extends BlockEntity {
    public HologramBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HOLOGRAM_BLOCK_ENTITY.get(), pos, state);
    }

    public String getHologramText() {
        return "Monkey";
    }
}   