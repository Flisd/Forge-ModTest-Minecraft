package net.agent.testmod.entity.custom;

import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.block.custom.DiceBlock;
import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
        if(!this.level().isClientSide()){
            this.level().broadcastEntityEvent(this, ((byte) 3));
            int blockPosX = blockPosition().getX();
            int blockPosY = blockPosition().getY();
            int blockPosZ = blockPosition().getZ();
            BlockPos newBlockPos = blockPosition().offset(blockPosX,blockPosY+1,blockPosZ);
            this.level().setBlock(newBlockPos,Blocks.FIRE.defaultBlockState(), 3);
            this.level().setBlock(blockPosition(), Blocks.TNT.defaultBlockState(), 3);
        }
        super.onHitBlock(p_37258_);
    }
}
