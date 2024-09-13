package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GreenLightBlock extends Block {
    public static final IntegerProperty BRIGHTNESS = IntegerProperty.create("brightness", 0, 3);

    public GreenLightBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BRIGHTNESS, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BRIGHTNESS);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.scheduleTick(pos, this, 10);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int brightness = state.getValue(BRIGHTNESS);
        if (brightness < 3) {
            world.setBlock(pos, state.setValue(BRIGHTNESS, brightness + 1), 3);
            world.scheduleTick(pos, this, 10);
        } else {
            world.setBlock(pos, state.setValue(BRIGHTNESS, 0), 3);
        }
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(BRIGHTNESS) * 5; // Adjust the multiplier as needed
    }
}