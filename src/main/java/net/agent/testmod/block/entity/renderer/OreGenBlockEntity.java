package net.agent.testmod.block.entity.renderer;

import net.agent.testmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OreGenBlockEntity extends BlockEntity {
    public OreGenBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ORE_GEN_BLOCK_ENTITY.get(), pos, state);
    }

    public void tick() {
        if (level != null && !level.isClientSide) {
            ((net.agent.testmod.block.custom.OreGenBlock) getBlockState().getBlock()).dispenseOre(level, worldPosition);
        }
    }
}
