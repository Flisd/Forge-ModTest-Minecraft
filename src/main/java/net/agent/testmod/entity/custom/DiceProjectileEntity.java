package net.agent.testmod.entity.custom;

import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.block.custom.DiceBlock;
import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public DiceProjectileEntity(Level p_37443_) {
        super(ModEntities.DICE_PROJECTILE.get(), p_37443_);
    }

    public DiceProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.DICE_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.DICE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this, ((byte) 3));
            this.level().setBlock(blockPosition(), ((DiceBlock) ModBlocks.DICE_BLOCK.get()).getRandomBlockState(), 3);
        }
        super.onHitBlock(p_37258_);
    }
}
