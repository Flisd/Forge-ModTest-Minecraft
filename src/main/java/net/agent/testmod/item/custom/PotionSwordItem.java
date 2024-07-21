package net.agent.testmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.*;
import java.util.List;

public class PotionSwordItem extends SwordItem {
    private static final int MAX_POTIONS = 3;
    private List<MobEffectInstance> potionEffects = new ArrayList<>();

    public PotionSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Random random = new Random();
        for (MobEffectInstance effect : potionEffects) {
            // 50% chance to apply each potion effect
            if (random.nextFloat() < 0.5) {
                target.addEffect(new MobEffectInstance(effect));
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    public void addPotionEffect(MobEffectInstance effect) {
        if (potionEffects.size() >= MAX_POTIONS) {
            // If the sword already has 3 potions, replace the first one
            potionEffects.remove(0);
        }
        potionEffects.add(effect);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        StringBuilder pots = new StringBuilder("Pots: ");
        for (int i = 0; i < potionEffects.size(); i++) {
            pots.append(potionEffects.get(i).getEffect().getDisplayName().getString());
            if (i < potionEffects.size() - 1) {
                pots.append(", ");
            }
        }
        p_41423_.add(Component.literal(pots.toString()));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

//    @Override
//    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
//        p_41423_.add(Component.translatable("tooltip.testmod.metal_detector.tooltip"));
//        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
//    }

}
