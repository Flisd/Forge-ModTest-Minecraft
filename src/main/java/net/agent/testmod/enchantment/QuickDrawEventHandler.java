package net.agent.testmod.enchantment;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class QuickDrawEventHandler {

    @SubscribeEvent
    public static void onProjectileLaunch(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        if (projectile instanceof AbstractArrow) {
            AbstractArrow arrow = (AbstractArrow) projectile;
            if (arrow.getOwner() instanceof Player) {
                Player player = (Player) arrow.getOwner();
                ItemStack bow = player.getMainHandItem();

                // Check if the bow has the QuickDraw enchantment
                int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICK_DRAW.get(), bow);
                if (enchantmentLevel > 0) {
                    // Adjust the arrow speed based on the enchantment level
                    float arrowSpeedMultiplier = 3.0F + (0.1F * enchantmentLevel);
                    arrow.setDeltaMovement(arrow.getDeltaMovement().scale(arrowSpeedMultiplier));
                }
            }
        }
    }
}