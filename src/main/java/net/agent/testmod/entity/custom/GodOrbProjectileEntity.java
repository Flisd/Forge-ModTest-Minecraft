package net.agent.testmod.entity.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.Random;

public class GodOrbProjectileEntity extends ThrowableItemProjectile {
    public GodOrbProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public GodOrbProjectileEntity(Level p_37443_) {
        super(ModEntities.GOD_ORB_PROJECTILE.get(), p_37443_);
    }

    public GodOrbProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.GOD_ORB_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FOOD_ORB.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        Level level = this.level();
        BlockPos hitPos = hitResult.getBlockPos();

        if (!level.isClientSide) {
            // Summon cobwebs
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos cobwebPos = hitPos.offset(x, 0, z);
                    level.setBlock(cobwebPos, Blocks.COBWEB.defaultBlockState(), 3);
                }
            }

            // Summon blazes
            Random random = new Random();
            int blazeCount = 7 + random.nextInt(4);
            for (int i = 0; i < blazeCount; i++) {
                int offsetX = random.nextInt(3) - 1;
                int offsetZ = random.nextInt(3) - 1;
                BlockPos blazePos = hitPos.offset(offsetX, 0, offsetZ);
                Blaze blaze = new Blaze(EntityType.BLAZE, level);
                blaze.setPos(blazePos.getX(), blazePos.getY(), blazePos.getZ());
                level.addFreshEntity(blaze);
            }

            // Apply slowness effect to player if hit
            List<Player> players = level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(3.0D));
            for (Player hitPlayer : players) {
                hitPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 3));
            }

            // Strike lightning
            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos lightningPos = hitPos.offset(x, 0, z);
                    LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                    lightningBolt.moveTo(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
                    level.addFreshEntity(lightningBolt);
                }
            }
        }
    }
}
