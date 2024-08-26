package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class BoomBowEventHandler {

    @SubscribeEvent
    public static void onArrowImpact(ProjectileImpactEvent event) {
        if (event.getProjectile().getOwner() instanceof Player) {
            Player player = (Player) event.getProjectile().getOwner();
            ItemStack bow = player.getMainHandItem();

            // Check if the bow has the BoomBow enchantment
            int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.BOW_BOOM.get(), bow);
            if (enchantmentLevel > 0) {
                if (!player.level().isClientSide) {
                    ServerLevel level = (ServerLevel) player.level();
                    BlockPos pos = event.getProjectile().blockPosition();

                    // Create an explosion at the arrow's impact position with size based on enchantment level
                    float explosionSize = enchantmentLevel == 1 ? 2.0F : 4.0F;
                    level.explode(null, pos.getX(), pos.getY(), pos.getZ(), explosionSize, Level.ExplosionInteraction.BLOCK);
                }
            }
        }
    }
}
