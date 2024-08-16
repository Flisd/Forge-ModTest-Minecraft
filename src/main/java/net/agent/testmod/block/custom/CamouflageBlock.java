package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CamouflageBlock extends Block {
    public static final BooleanProperty MIMIC = BooleanProperty.create("mimic");

    public CamouflageBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(MIMIC, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(MIMIC);
    }

    @Override
    public void onPlace(BlockState blockState1, Level level, BlockPos blockPos, BlockState blockState2, boolean p_60570_) {
        super.onPlace(blockState1, level, blockPos, blockState2, p_60570_);

        // Find the closest block and set its properties
        BlockPos[] positions = {
                blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.above(), blockPos.below()
        };

        for (BlockPos pos : positions) {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() != Blocks.AIR) {
                level.setBlock(blockPos, this.defaultBlockState().setValue(MIMIC, true), 3);
                break;
            }
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, level, pos, block, fromPos, isMoving);

        // Check for support blocks
        BlockPos[] positions = {
                pos.north(), pos.south(), pos.east(), pos.west(), pos.above(), pos.below()
        };

        boolean hasSupport = false;
        for (BlockPos supportPos : positions) {
            if (level.getBlockState(supportPos).getBlock() != Blocks.AIR) {
                hasSupport = true;
                break;
            }
        }

        // If no support blocks, break the block
        if (!hasSupport) {
            level.destroyBlock(pos, true);
        }
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal("It likes to blend in... [WIP DOES NOT WORK]"));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }
}
