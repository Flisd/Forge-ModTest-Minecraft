package net.agent.testmod.block.custom;

import net.agent.testmod.item.ModItems;
import net.agent.testmod.item.custom.KitCrateKeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class KitCrateBox extends Block {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    private static final Random RANDOM = new Random();

    public KitCrateBox(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (itemStack.getItem() instanceof KitCrateKeyItem) {
            itemStack.setCount(itemStack.getCount() - 1);
            // Open the crate
            world.setBlock(pos, state.setValue(OPEN, true), 3);

            // Give random kit item
            ItemStack kitItem = getRandomKitItem();
            kitItem.enchant(Enchantments.UNBREAKING,1);
            addItemOrDrop((ServerPlayer) player, kitItem);

            world.scheduleTick(pos, this, 20);

            return InteractionResult.SUCCESS;
        } else {
            // Throw the player back a couple of blocks
            player.knockback(1.5F, pos.getX() - player.getX(), pos.getZ() - player.getZ());
            return InteractionResult.FAIL;
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        // Close the crate
        world.setBlock(pos, state.setValue(OPEN, false), 3);
    }

    private ItemStack getRandomKitItem() {
        int randomValue = RANDOM.nextInt(100) + 1;

        if (randomValue <= 12) {
            return new ItemStack(ModItems.ADVANCED_KIT_ITEM.get());
        } else if (randomValue <= 27) {
            return new ItemStack(ModItems.FOOD_KIT_ITEM.get());
        } else if (randomValue <= 31) {
            return new ItemStack(ModItems.GOD_KIT_ITEM.get());
        } else if (randomValue <= 35) {
            return new ItemStack(ModItems.MONKEY_KIT_ITEM.get());
        } else if (randomValue <= 55) {
            return new ItemStack(ModItems.PREMIUM_KIT_ITEM.get());
        } else if (randomValue <= 85) {
            return new ItemStack(ModItems.STARTER_KIT_ITEM.get());
        } else {
            return new ItemStack(ModItems.TOTEM_KIT_ITEM.get());
        }
    }

    public static void addItemOrDrop(ServerPlayer player, ItemStack itemStack) {
        if (player.getInventory().add(itemStack)) {
            // Item successfully added to inventory
        } else {
            Level world = player.level();
            BlockPos pos = player.blockPosition();
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
        }
    }
}
