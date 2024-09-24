package net.agent.testmod.event;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.agent.testmod.block.custom.ExperienceStorageBlock;

import java.util.List;

@Mod.EventBusSubscriber
public class ExperienceStorageEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level world = player.level();

        // Iterate through all loaded chunks
        for (BlockPos pos : BlockPos.betweenClosed(player.blockPosition().offset(-10, -10, -10), player.blockPosition().offset(10, 10, 10))) {
            if (world.getBlockState(pos).getBlock() instanceof ExperienceStorageBlock) {
                ExperienceStorageBlock xpBlock = (ExperienceStorageBlock) world.getBlockState(pos).getBlock();
                BlockPos blockPos = pos;

                // Check if the block is in ABSORBINGXP mode
                if (world.getBlockState(pos).getValue(ExperienceStorageBlock.ABSORBINGXP)) {
                    // Check for nearby XP orbs
                    AABB area = new AABB(blockPos).inflate(5);
                    List<ExperienceOrb> xpOrbs = world.getEntitiesOfClass(ExperienceOrb.class, area);
                    for (ExperienceOrb xpOrb : xpOrbs) {
                        if (xpBlock.getStoredXP() < ExperienceStorageBlock.MAX_XP) {
                            xpBlock.addXP(xpOrb.getValue());
                            xpOrb.remove(Entity.RemovalReason.DISCARDED);
                            xpBlock.updateBrightness(world, blockPos);
                        }
                    }
                }
            }
        }
    }
}
