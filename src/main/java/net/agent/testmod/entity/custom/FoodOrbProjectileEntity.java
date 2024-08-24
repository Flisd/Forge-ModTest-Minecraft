package net.agent.testmod.entity.custom;

import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class FoodOrbProjectileEntity extends ThrowableItemProjectile {
    public FoodOrbProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public FoodOrbProjectileEntity(Level p_37443_) {
        super(ModEntities.FOOD_ORB_PROJECTILE.get(), p_37443_);
    }

    public FoodOrbProjectileEntity(Level p_37443_, LivingEntity livingEntity) {
        super(ModEntities.FOOD_ORB_PROJECTILE.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.FOOD_ORB.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, ((byte) 3));
            BlockPos blockPos = blockPosition();
            Random random = new Random();

            // List of possible food items (excluding golden apples and enchanted golden apples)
            Item[] foodItems = {
                    Items.APPLE, Items.BREAD, Items.CARROT, Items.POTATO, Items.BEEF,
                    Items.CHICKEN, Items.PORKCHOP, Items.MUTTON, Items.COOKED_BEEF,
                    Items.COOKED_CHICKEN, Items.COOKED_PORKCHOP, Items.COOKED_MUTTON
            };

            // Select a random food item from the list
            Item selectedFoodItem = foodItems[random.nextInt(foodItems.length)];

            // Spawn 8 pieces of the selected food item
            for (int i = 0; i < 1; i++) {
                ItemEntity foodEntity = new ItemEntity(this.level(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, new ItemStack(selectedFoodItem));
                this.level().addFreshEntity(foodEntity);
            }

            // 5% chance to spawn 5 golden apples
            if (random.nextInt(100) < 5) {
                for (int i = 0; i < 5; i++) {
                    ItemEntity goldenAppleEntity = new ItemEntity(this.level(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, new ItemStack(Items.GOLDEN_APPLE));
                    this.level().addFreshEntity(goldenAppleEntity);
                }
            }

            // 1% chance to spawn 1 enchanted golden apple
            if (random.nextInt(100) < 1) {
                ItemEntity enchantedGoldenAppleEntity = new ItemEntity(this.level(), blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
                this.level().addFreshEntity(enchantedGoldenAppleEntity);
            }
        }
        super.onHitBlock(p_37258_);
    }
}
