package net.agent.testmod.entity.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import java.util.Random;

public class WaterLavaProjectileEntity extends ThrowableItemProjectile {
    private final Random random = new Random();

    public WaterLavaProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public WaterLavaProjectileEntity(Level p_37443_) {
        super(ModEntities.WATER_LAVA_PROJECTILE.get(), p_37443_);
    }

    public WaterLavaProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.WATER_LAVA_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.WATER_LAVA.get();
    }

    @Override
    protected void onHit(HitResult p_37406_) {
        super.onHit(p_37406_);
        if (!this.level().isClientSide) {
            BlockPos pos = new BlockPos((int)p_37406_.getLocation().x,(int) p_37406_.getLocation().y,(int) p_37406_.getLocation().z);
            if (random.nextBoolean()) {
                // 50% chance to summon water
                this.level().setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
            } else {
                // 50% chance to summon lava
                this.level().setBlock(pos, Blocks.LAVA.defaultBlockState(), 3);
            }
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }
}
