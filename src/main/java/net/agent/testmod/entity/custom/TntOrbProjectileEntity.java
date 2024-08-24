package net.agent.testmod.entity.custom;

import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.block.custom.DiceBlock;
import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class TntOrbProjectileEntity extends ThrowableItemProjectile {
    public TntOrbProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public TntOrbProjectileEntity(Level p_37443_) {
        super(ModEntities.TNT_ORB_PROJECTILE.get(), p_37443_);
    }

    public TntOrbProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.TNT_ORB_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TNT_ORB.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            BlockPos blockPos = blockPosition().above(); // Get the position above the hit block
            PrimedTnt primedTnt = new PrimedTnt(this.level(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, null);
            this.level().addFreshEntity(primedTnt);
        }
        super.onHitBlock(p_37258_);
    }
}
