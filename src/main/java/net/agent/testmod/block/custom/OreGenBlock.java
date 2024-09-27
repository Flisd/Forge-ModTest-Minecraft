package net.agent.testmod.block.custom;

import net.agent.testmod.block.entity.renderer.OreGenBlockEntity;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class OreGenBlock extends Block {
    private static final Random RANDOM = new Random();

    public OreGenBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new OreGenBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return (lvl, pos, blockState, t) -> {
            if (t instanceof OreGenBlockEntity blockEntity) {
                blockEntity.tick();
            }
        };
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 600);
        }
        super.onPlace(state, world, pos, oldState, isMoving);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        dispenseOre(world, pos);
        world.scheduleTick(pos, this, 600);
    }

    public void dispenseOre(Level world, BlockPos pos) {
        Item selectedOre = selectRandomOre();
        if (!world.isClientSide) {
            ItemEntity ore = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, new ItemStack(selectedOre));
            world.addFreshEntity(ore);
        }
    }

    private Item selectRandomOre() {
        int randomValue = RANDOM.nextInt(100) + 1; // Random number between 1 and 100

        if (randomValue <= 2) {
            return Items.NETHERITE_INGOT; // 1%
        } else if (randomValue <= 6) {
            return Items.DIAMOND; // 5%
        } else if (randomValue <= 20) {
            return Items.GOLD_INGOT; // 14%
        } else if (randomValue <= 40) {
            return Items.IRON_INGOT; // 20%
        } else if (randomValue <= 60) {
            return Items.COAL; // 20%
        } else if (randomValue <= 80) {
            return Items.LAPIS_LAZULI; // 20%
        } else {
            return ModItems.SAPPHIRE.get(); // 20%
        }
    }
}
