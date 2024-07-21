package net.agent.testmod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import java.util.*;

public class PosionSwordItem extends SwordItem {

    public PosionSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        // teir
        // attack damaghe
        // attack speed
        // item properties
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        // 10% chance to apply the effect
        if (random.nextFloat() < 0.1) {
            // Apply the poison effect for 5 seconds at level 2
            target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1));
        }

        return super.hurtEnemy(stack, target, attacker);
    }

}