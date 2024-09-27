package net.agent.testmod.item.custom;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class HowDidWeGetHerePot extends SplashPotionItem {
    public HowDidWeGetHerePot(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (!world.isClientSide) {
            ItemStack customPotion = new ItemStack(Items.SPLASH_POTION);
            PotionUtils.setPotion(customPotion, Potions.EMPTY);

            List<MobEffectInstance> customEffects = Lists.newArrayList();
            customEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1000, 2));
            customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.NIGHT_VISION, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.ABSORPTION, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.DIG_SPEED, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.GLOWING, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.JUMP, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.INVISIBILITY, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.WATER_BREATHING, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.SATURATION, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.HEALTH_BOOST, 1000, 4));
            customEffects.add(new MobEffectInstance(MobEffects.HARM, 1, 1));
            customEffects.add(new MobEffectInstance(MobEffects.POISON, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.WITHER, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.HUNGER, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.BAD_OMEN, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.CONFUSION, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.SLOW_FALLING, 400, 1));
            customEffects.add(new MobEffectInstance(MobEffects.WEAKNESS, 200, 1));
            customEffects.add(new MobEffectInstance(MobEffects.LEVITATION, 1000, 1));
            customEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1000, 1));

            PotionUtils.setCustomEffects(customPotion, customEffects);

            ThrownPotion thrownPotion = new ThrownPotion(world, player);
            thrownPotion.setItem(customPotion);
            thrownPotion.setDeltaMovement(Vec3.ZERO);
            world.addFreshEntity(thrownPotion);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
    }

    @Override
    public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int count) {
        super.onUseTick(world, entity, stack, count);
    }
}
