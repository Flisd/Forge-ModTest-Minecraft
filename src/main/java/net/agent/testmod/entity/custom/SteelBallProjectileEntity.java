package net.agent.testmod.entity.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SteelBallProjectileEntity extends ThrowableItemProjectile {
    public SteelBallProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public SteelBallProjectileEntity(Level p_37443_) {
        super(ModEntities.STEEL_BALL_PROJECTILE.get(), p_37443_);
    }

    public SteelBallProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.STEEL_BALL_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.STEEL_BALL.get();
    }

    protected void onHitEntity(EntityHitResult p_37404_) {
        super.onHitEntity(p_37404_);
        Entity entity = p_37404_.getEntity();
        int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);

        double knockbackStrength = 1.5; // Adjust this value to increase or decrease knockback
        double xRatio = -Math.sin(this.getYRot() * Math.PI / 180.0);
        double zRatio = Math.cos(this.getYRot() * Math.PI / 180.0);
        entity.setDeltaMovement(entity.getDeltaMovement().add(xRatio * knockbackStrength, 0.1, zRatio * knockbackStrength));
    }


    protected void onHit(HitResult p_37406_) {
        super.onHit(p_37406_);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }

    }
}
