package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class VortexBlock extends Block {
    public static final IntegerProperty PULL_STRENGTH = IntegerProperty.create("pull_strength", 0, 3);
    private static final int RADIUS = 10;  // Define the radius of the vortex

    public VortexBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(PULL_STRENGTH, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PULL_STRENGTH);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        world.scheduleTick(pos, this, 10);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        int pullStrength = state.getValue(PULL_STRENGTH);
        if (pullStrength < 3) {
            world.setBlock(pos, state.setValue(PULL_STRENGTH, pullStrength + 1), 3);
            world.scheduleTick(pos, this, 10);
        } else {
            world.setBlock(pos, state.setValue(PULL_STRENGTH, 0), 3);
        }

        // Apply vortex effect
        applyVortex(world, pos, pullStrength);
    }

    private void applyVortex(ServerLevel world, BlockPos pos, int pullStrength) {
        // Get all living entities in a radius
        AABB aabb = new AABB(pos).inflate(RADIUS);
        List<Entity> entities = world.getEntities(null, aabb);

        Vec3 blockCenter = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);

        // Apply pulling force to each living entity
        for (Entity entity : entities) {
                Vec3 entityPos = entity.position();
                Vec3 pullVector = blockCenter.subtract(entityPos).normalize().scale(pullStrength * 0.95);

                // Special case for players to ensure pull effect
                if (entity instanceof Player) {
                    Player player = (Player) entity;

                    // Check if the player is in survival or adventure mode
                    if (!player.isCreative() && !player.isSpectator()) {
                        // Apply the pull effect to the player
                        player.setDeltaMovement(player.getDeltaMovement().add(pullVector));
                        player.setDeltaMovement(player.getDeltaMovement().scale(0.95));
                    }
                } else {
                    entity.setDeltaMovement(entity.getDeltaMovement().add(pullVector));
                    entity.setDeltaMovement(entity.getDeltaMovement().scale(0.95));
                }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level world = player.level();

        // Check if the player is in survival or adventure mode
        if (!player.isCreative() && !player.isSpectator()) {
            BlockPos playerPos = player.blockPosition();

            // Check for nearby VortexBlocks
            for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-10, -10, -10), playerPos.offset(10, 10, 10))) {
                if (world.getBlockState(pos).getBlock() instanceof VortexBlock) {
                    VortexBlock vortexBlock = (VortexBlock) world.getBlockState(pos).getBlock();
                    int pullStrength = world.getBlockState(pos).getValue(VortexBlock.PULL_STRENGTH);

                    Vec3 blockCenter = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                    Vec3 pullVector = blockCenter.subtract(player.position()).normalize().scale(pullStrength * 0.45);

                    // Apply the pull effect to the player
                    player.setDeltaMovement(player.getDeltaMovement().add(pullVector));
                    player.setDeltaMovement(player.getDeltaMovement().scale(0.95));
                }
            }
        }
    }
}