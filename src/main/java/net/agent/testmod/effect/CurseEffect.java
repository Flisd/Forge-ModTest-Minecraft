package net.agent.testmod.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class CurseEffect extends MobEffect {
    private static final Random random = new Random();

    public CurseEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF0000); // Red color for the effect
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide) {
            Level world = entity.level();
            int spawnInterval = getSpawnInterval(amplifier);
            if (entity.tickCount % spawnInterval == 0) {
                EntityType<?> mobType = getMobType(amplifier);
                if (mobType != null && world instanceof ServerLevel) {
                    mobType.spawn((ServerLevel) world, (ItemStack) null, null, entity.blockPosition(), MobSpawnType.TRIGGERED, true, true);
                }
            }
        }
        super.applyEffectTick(entity, amplifier);
    }

    private int getSpawnInterval(int amplifier) {
        switch (amplifier) {
            case 0: return 60; // Level 1: 3 seconds (60 ticks)
            case 1: return 20; // Level 2: 1 second (20 ticks)
            case 2: return 60; // Level 3: 3 seconds (60 ticks)
            case 3: return 60; // Level 4: 3 seconds (60 ticks)
            case 4: return 300; // Level 5: 15 seconds (300 ticks)
            default: return 60;
        }
    }

    private EntityType<?> getMobType(int amplifier) {
        switch (amplifier) {
            case 0: return getRandomTier1Mob();
            case 1: return getRandomTier1Mob();
            case 2: return getRandomTier2Mob();
            case 3: return getRandomTier3Mob();
            case 4: return getRandomTier4Mob();
            default: return null;
        }
    }

    private EntityType<?> getRandomTier1Mob() {
        EntityType<?>[] tier1Mobs = {EntityType.SKELETON, EntityType.ZOMBIE, EntityType.SPIDER};
        return tier1Mobs[random.nextInt(tier1Mobs.length)];
    }

    private EntityType<?> getRandomTier2Mob() {
        EntityType<?>[] tier2Mobs = {EntityType.STRAY, EntityType.CAVE_SPIDER, EntityType.ZOMBIE};
        return tier2Mobs[random.nextInt(tier2Mobs.length)];
    }

    private EntityType<?> getRandomTier3Mob() {
        EntityType<?>[] tier3Mobs = {EntityType.BLAZE, EntityType.WITHER_SKELETON, EntityType.GHAST, EntityType.ELDER_GUARDIAN};
        return tier3Mobs[random.nextInt(tier3Mobs.length)];
    }

    private EntityType<?> getRandomTier4Mob() {
        EntityType<?>[] tier4Mobs = {EntityType.WARDEN, EntityType.WITHER};
        return tier4Mobs[random.nextInt(tier4Mobs.length)];
    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
