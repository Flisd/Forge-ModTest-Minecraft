package net.agent.testmod.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.event.entity.living.MobEffectEvent;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.5f)
            .effect(()->new MobEffectInstance(MobEffects.REGENERATION,200), 0.5f)
            .effect(()->new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200), 0.8f)
            .effect(()->new MobEffectInstance(MobEffects.GLOWING,200), 1f)
            .effect(()->new MobEffectInstance(MobEffects.ABSORPTION,400), 1f)
            .alwaysEat()
            .build();
}
