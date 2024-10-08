package net.agent.testmod.item.custom;

import net.agent.testmod.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PotionSwordItem extends SwordItem {
    private static final int MAX_POTIONS = 3;
    public static List<MobEffectInstance> potionEffects = new ArrayList<>();

    public PotionSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
        // 200 ticks = 10 seconds, amplifier = 1 for Slowness II
        potionEffects.add(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
        potionEffects.add(new MobEffectInstance(MobEffects.HARM, 30, 1));
        potionEffects.add(new MobEffectInstance(MobEffects.REGENERATION, 200, 1));
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

    public static void addPotionEffect(MobEffectInstance effect) {
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

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (level.isClientSide()) {
            spawnParticles(level, player);
        }
        return InteractionResultHolder.success(itemstack);
    }

    private void spawnParticles(Level level, Player player) {
        double x = player.getX();
        double y = player.getY() + player.getEyeHeight();
        double z = player.getZ();
        double dx = player.getLookAngle().x * 0.5;
        double dy = player.getLookAngle().y * 0.5;
        double dz = player.getLookAngle().z * 0.5;

        for (int i = 0; i < 50; i++) {
            BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
            if (!level.isEmptyBlock(pos)) {
                break;
            }
            level.addParticle(ModParticles.BLIND_PARTICLES.get(), x, y, z, 0, 0, 0);
            x += dx;
            y += dy;
            z += dz;
        }
    }
}
