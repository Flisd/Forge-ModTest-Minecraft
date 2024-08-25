package net.agent.testmod.entity.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class PortalOrbProjectileEntity extends ThrowableItemProjectile {
    public PortalOrbProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public PortalOrbProjectileEntity(Level p_37443_) {
        super(ModEntities.PORTAL_ORB_PROJECTILE.get(), p_37443_);
    }

    public PortalOrbProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.PORTAL_ORB_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.PORTAL_ORB.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        Level level = this.level();
        BlockPos hitPos = hitResult.getBlockPos();

        if (!level.isClientSide) {
            // Manually place all blocks to create the portal structure
            level.setBlock(hitPos.offset(-1, 1, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(-1, 2, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(-1, 3, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(-1, 4, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(2, 1, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(2, 2, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(2, 3, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(2, 4, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(0, 4, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(1, 4, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(0, 0, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(1, 0, 0), Blocks.OBSIDIAN.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(0, 1, 0), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(1, 1, 0), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(0, 2, 0), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(1, 2, 0), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(0, 3, 0), Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(hitPos.offset(1, 3, 0), Blocks.AIR.defaultBlockState(), 3);

            // Light the portal on fire
            level.setBlock(hitPos.offset(0, 1, 0), Blocks.FIRE.defaultBlockState(), 3);
        }
    }
}
