package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ExperienceStorageBlock extends Block {
    public static final IntegerProperty BRIGHTNESS = IntegerProperty.create("brightness", 0, 3);
    public static final BooleanProperty ABSORBINGXP = BooleanProperty.create("absorbingxp");
    public static final int MAX_XP = 10000;
    private int storedXP = 0;

    public ExperienceStorageBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(BRIGHTNESS, 0).setValue(ABSORBINGXP, true));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BRIGHTNESS, ABSORBINGXP);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        if (state.getValue(ABSORBINGXP)) {
            AABB area = new AABB(pos).inflate(5);
            List<ExperienceOrb> xpOrbs = world.getEntitiesOfClass(ExperienceOrb.class, area);
            for (ExperienceOrb xpOrb : xpOrbs) {
                if (storedXP < MAX_XP) {
                    storedXP += xpOrb.getValue();
                    xpOrb.remove(Entity.RemovalReason.DISCARDED);
                    updateBrightness(world, pos);
                }
            }
        } else {
            releaseXP(world, pos);
        }
        world.scheduleTick(pos, this, 10);
    }

    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player) {
            world.setBlock(pos, state.setValue(ABSORBINGXP, false), 3);
        }
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity instanceof Player) {
            world.setBlock(pos, state.setValue(ABSORBINGXP, false), 3);
        }
    }

    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (world.hasNeighborSignal(pos)) {
            releaseXP(world, pos);
        }
    }

    public void releaseXP(Level world, BlockPos pos) {
        if (storedXP > 0) {
            int xpToRelease = Math.min(storedXP, 10); // Release 10 XP at a time
            storedXP -= xpToRelease;
            for (int i = 0; i < xpToRelease; i++) {
                double offsetX = (world.random.nextDouble() - 0.5) * 2;
                double offsetY = world.random.nextDouble();
                double offsetZ = (world.random.nextDouble() - 0.5) * 2;
                ExperienceOrb xpOrb = new ExperienceOrb(world, pos.getX() + 0.5 + offsetX, pos.getY() + 1 + offsetY, pos.getZ() + 0.5 + offsetZ, 1);
                world.addFreshEntity(xpOrb);
            }
            updateBrightness(world, pos);
        }
    }

    public int getStoredXP() {
        return storedXP;
    }

    public void addXP(int xp) {
        storedXP = Math.min(storedXP + xp, MAX_XP);
    }

    public void updateBrightness(Level world, BlockPos pos) {
        int brightness = Math.min(storedXP / 100, 3); // Adjust the divisor as needed
        world.setBlock(pos, this.defaultBlockState().setValue(BRIGHTNESS, brightness), 3);
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(BRIGHTNESS) * 5; // Adjust the multiplier as needed
    }
}
