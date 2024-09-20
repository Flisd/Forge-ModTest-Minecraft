package net.agent.testmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.agent.testmod.block.custom.VortexBlock;

@Mod.EventBusSubscriber
public class VortexEventHandler {

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
                    Vec3 pullVector = blockCenter.subtract(player.position()).normalize().scale(pullStrength * 1.25);

                    // Apply the pull effect to the player
                    player.setDeltaMovement(player.getDeltaMovement().add(pullVector));
                    player.setDeltaMovement(player.getDeltaMovement().scale(0.95));
                }
            }
        }
    }
}