package net.agent.testmod.block.custom;

import net.agent.testmod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class DispenserSquareTileEntity extends BlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(27, ItemStack.EMPTY);

    public DispenserSquareTileEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DISPENSER_SQUARE.get(), pos, state);
    }

    public void spitOutItem() {
        for (int i = 0; i < items.size(); i++) {
            ItemStack stack = items.get(i);
            if (!stack.isEmpty()) {
                ItemStack spitStack = stack.split(stack.getCount() - 1);
                ItemEntity itemEntity = new ItemEntity(level, worldPosition.getX() + 0.5, worldPosition.getY() + 1, worldPosition.getZ() + 0.5, spitStack);
                level.addFreshEntity(itemEntity);
                break;
            }
        }
    }

    public void tick() {
        // Implement any periodic logic here if needed
    }

    // Implement other necessary methods for inventory management
}
