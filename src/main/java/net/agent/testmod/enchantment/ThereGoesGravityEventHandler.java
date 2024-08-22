package net.agent.testmod.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class ThereGoesGravityEventHandler {

    @SubscribeEvent
    public static void onPlayerAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();
        ItemStack weapon = player.getMainHandItem();

        // Check if the weapon has the "There Goes Gravity" enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.THERE_GOES_GRAVITY.get(), weapon) > 0) {
            Random random = new Random();
            int chance = random.nextInt(100);

            // 3-7% chance to give the target levitation
            if (chance < 7) {
                if (target instanceof LivingEntity) {
                    ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60 + random.nextInt(40), 0)); // 3-5 seconds
                }
            }

            // 1% chance to toss the target 10 blocks in the air
            if (chance <20 && chance > 10) {
                if (target instanceof LivingEntity) {
                    Vec3 velocity = target.getDeltaMovement();
                    target.setDeltaMovement(velocity.x, 1.0D, velocity.z);
                }
            }
        }
    }
}
