package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BeaconSquareBlock extends Block {
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public BeaconSquareBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(LIT, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal(" (Beacon⁴ / Beacon³ ) Beacon"));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            if (isValidStructure(world, pos)) {
                world.setBlock(pos, state.setValue(LIT, true), 3);
                emitBeams(world, pos);
            } else {
                world.setBlock(pos, state.setValue(LIT, false), 3);
            }
        }
    }

    private boolean isValidStructure(Level world, BlockPos pos) {
        // Check for 3x3 netherite block structure
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (!world.getBlockState(pos.offset(x, -1, z)).is(net.minecraft.world.level.block.Blocks.NETHERITE_BLOCK)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void emitBeams(Level world, BlockPos pos) {
        // Emit beams in all directions
        emitBeam(world, pos, new Vec3(0, 1, 0)); // Up
        emitBeam(world, pos, new Vec3(0, 0, 1)); // North
        emitBeam(world, pos, new Vec3(0, 0, -1)); // South
        emitBeam(world, pos, new Vec3(1, 0, 0)); // East
        emitBeam(world, pos, new Vec3(-1, 0, 0)); // West
    }

    private void emitBeam(Level world, BlockPos pos, Vec3 direction) {
        BlockPos.MutableBlockPos mutablePos = pos.mutable();
        while (true) {
            mutablePos.move((int) direction.x, (int) direction.y, (int) direction.z);
            BlockState blockState = world.getBlockState(mutablePos);
            if (blockState.isAir() || blockState.is(net.minecraft.world.level.block.Blocks.GLASS) || blockState.is(net.minecraft.world.level.block.Blocks.GLASS_PANE)) {
                // Continue beam
            } else {
                break; // Stop beam
            }
        }
    }
}
