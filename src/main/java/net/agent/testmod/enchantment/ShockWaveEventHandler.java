package net.agent.testmod.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class ShockWaveEventHandler {

    private static final int SHOCKWAVE_RADIUS = 5;
    private static final int STUN_DURATION = 60; // 3 seconds (20 ticks per second)

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack tool = player.getItemBySlot(EquipmentSlot.MAINHAND);

        // Check if the tool has the ShockWave enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.SHOCKWAVE.get(), tool) > 0) {
            if (!player.level().isClientSide) {
                ServerLevel level = (ServerLevel) player.level();
                AABB area = new AABB(player.blockPosition()).inflate(SHOCKWAVE_RADIUS);

                // Get all entities within the shockwave radius
                List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, area, e -> e != player);

                // Apply stun effect to all entities within the radius
                for (LivingEntity entity : entities) {
                    entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, STUN_DURATION, 4)); // Stun effect
                }
            }
        }
    }
}
