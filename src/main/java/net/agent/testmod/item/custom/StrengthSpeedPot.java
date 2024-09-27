package net.agent.testmod.item.custom;

import com.google.common.collect.Lists;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StrengthSpeedPot extends SplashPotionItem {
    public StrengthSpeedPot(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!world.isClientSide) {
            ItemStack customPotion = new ItemStack(Items.SPLASH_POTION);
            PotionUtils.setPotion(customPotion, Potions.EMPTY);

            List<MobEffectInstance> customEffects = Lists.newArrayList();
            customEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1800, 1));
            customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1800, 1));

            PotionUtils.setCustomEffects(customPotion, customEffects);

            ThrownPotion thrownPotion = new ThrownPotion(world, player);
            thrownPotion.setItem(customPotion);
            thrownPotion.setDeltaMovement(Vec3.ZERO);
            world.addFreshEntity(thrownPotion);

            // Decrease the item stack count
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, itemstack);
    }

    @Override
    public void onUseTick(Level world, LivingEntity entity, ItemStack stack, int count) {
        super.onUseTick(world, entity, stack, count);
    }
}