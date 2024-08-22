package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.Random;

public class DiamondTNTBlock extends TntBlock {
    public DiamondTNTBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    public void explode(Level world, BlockPos pos, @Nullable LivingEntity igniter) {
        if (!world.isClientSide) {
            if (!world.getBlockState(pos).isAir()) { // Ensure it only explodes once
                world.explode(null, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 5.0F, Level.ExplosionInteraction.TNT); // Reduced explosion power
                showerDiamonds(world, pos);
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3); // Set block to air after explosion
            }
        }
    }

    private void showerDiamonds(Level world, BlockPos pos) {
        Random random = new Random();
        int randomDia = random.nextInt(7) + 5;
        for (int i = 0; i < randomDia; i++) { // Number of diamonds to drop
            double offsetX = world.random.nextDouble() * 2.0 - 1.0;
            double offsetY = world.random.nextDouble() * 2.0 - 1.0;
            double offsetZ = world.random.nextDouble() * 2.0 - 1.0;
            ItemStack diamond = new ItemStack(Items.DIAMOND);
            Block.popResource(world, pos.offset((int) offsetX, (int) offsetY, (int) offsetZ), diamond);
        }
    }
}
