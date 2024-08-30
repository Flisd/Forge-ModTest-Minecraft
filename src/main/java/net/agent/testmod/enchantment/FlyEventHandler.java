package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FlyEventHandler {

    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent event) {
        if (event.getProjectile().getOwner() instanceof Player) {
            Player player = (Player) event.getProjectile().getOwner();
            ItemStack bow = player.getMainHandItem();
            // Check if the bow has the BoomBow enchantment
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FLY.get(), bow);
            if (enchantmentLevel > 0) {
                if (!player.level().isClientSide) {
                    ServerLevel level = (ServerLevel) player.level();
                    BlockPos pos = event.getProjectile().blockPosition();

                    // Check if the hit result is an entity
                    if (event.getRayTraceResult() instanceof EntityHitResult) {
                        Entity target = ((EntityHitResult) event.getRayTraceResult()).getEntity();

                        target.setDeltaMovement(target.getDeltaMovement().add(0, 10 + enchantmentLevel * 4, 0));

                        // Destroy the arrow after the impact
                        event.getProjectile().discard();
                    }
                }
            }
        }
    }
}