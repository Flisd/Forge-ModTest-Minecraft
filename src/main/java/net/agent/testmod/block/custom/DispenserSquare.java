package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class DispenserSquare extends Block {
    public DispenserSquare(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new net.agent.testmod.block.custom.DispenserSquareTileEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, t) -> {
            if (t instanceof net.agent.testmod.block.custom.DispenserSquareTileEntity blockEntity) {
                blockEntity.tick();
            }
        };
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (world.hasNeighborSignal(pos)) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity instanceof net.agent.testmod.block.custom.DispenserSquareTileEntity) {
                ((net.agent.testmod.block.custom.DispenserSquareTileEntity) tileEntity).spitOutItem();
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
    }
}