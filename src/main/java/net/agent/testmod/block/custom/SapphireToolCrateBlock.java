package net.agent.testmod.block.custom;

import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.agent.testmod.item.custom.SapphireToolCrateKeyItem;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class SapphireToolCrateBlock extends Block {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    private static final Random RANDOM = new Random();

    public SapphireToolCrateBlock(Properties properties) {
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

        if (itemStack.getItem() instanceof SapphireToolCrateKeyItem) {
            // Open the crate
            world.setBlock(pos, state.setValue(OPEN, true), 3);
            ItemStack randomTool = getRandomMaxedTool();
            player.addItem(randomTool);

            // Schedule the crate to close after a short delay
            world.scheduleTick(pos, this, 20); // 20 ticks = 1 second

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

    private ItemStack getRandomMaxedTool() {
        ItemStack tool = switch (RANDOM.nextInt(15)) {
            case 0 -> new ItemStack(ModItems.SAPPHIRE_SWORD.get());
            case 1 -> new ItemStack(ModItems.SAPPHIRE_AXE.get());
            case 2 -> new ItemStack(ModItems.SAPPHIRE_PICKAXE.get());
            case 3 -> new ItemStack(ModItems.SAPPHIRE_SHOVEL.get());
            case 4 -> new ItemStack(ModItems.SAPPHIRE_HOE.get());
            case 5 -> new ItemStack(Items.NETHERITE_SWORD);
            case 6 -> new ItemStack(Items.NETHERITE_AXE);
            case 7 -> new ItemStack(Items.NETHERITE_PICKAXE);
            case 8 -> new ItemStack(Items.NETHERITE_SHOVEL);
            case 9 -> new ItemStack(Items.NETHERITE_HOE);
            case 10 -> new ItemStack(Items.DIAMOND_SWORD);
            case 11 -> new ItemStack(Items.DIAMOND_AXE);
            case 12 -> new ItemStack(Items.DIAMOND_PICKAXE);
            case 13 -> new ItemStack(Items.DIAMOND_SHOVEL);
            case 14 -> new ItemStack(Items.DIAMOND_HOE);
            default -> throw new IllegalStateException();
        };

        // basic max out
        tool.enchant(Enchantments.MENDING, 1);
        tool.enchant(Enchantments.UNBREAKING, 3);
        tool.enchant(ModEnchantments.REJUVENATION.get(), 1);

        if (tool.getItem() == ModItems.SAPPHIRE_SWORD.get() || tool.getItem() == Items.NETHERITE_SWORD) {
            tool.enchant(Enchantments.SHARPNESS, 5);
            tool.enchant(Enchantments.KNOCKBACK, 1);
            tool.enchant(Enchantments.FIRE_ASPECT, 2);

            switch (RANDOM.nextInt(2)) {
                case 0 -> tool.enchant(ModEnchantments.LIGHTING_STRIKER.get(), 3);
                case 1 -> tool.enchant(ModEnchantments.THERE_GOES_GRAVITY.get(), 1);
            }
        } else if (tool.getItem() == ModItems.SAPPHIRE_AXE.get() || tool.getItem() == Items.NETHERITE_AXE) {
            tool.enchant(Enchantments.SHARPNESS, 5);
            tool.enchant(Enchantments.BLOCK_EFFICIENCY, 5);

            tool.enchant(ModEnchantments.TREE_BYE.get(), 1);
        } else if (tool.getItem() == ModItems.SAPPHIRE_PICKAXE.get() || tool.getItem() == Items.NETHERITE_PICKAXE) {
            tool.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            tool.enchant(Enchantments.BLOCK_FORTUNE, 3);

            tool.enchant(ModEnchantments.STRIP_MINING.get(), 1);
        } else if (tool.getItem() == ModItems.SAPPHIRE_SHOVEL.get() || tool.getItem() == Items.NETHERITE_SHOVEL) {
            tool.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            tool.enchant(Enchantments.BLOCK_FORTUNE, 3);

            tool.enchant(ModEnchantments.DOUBLE_TROUBLE.get(), 1);
        } else if (tool.getItem() == ModItems.SAPPHIRE_HOE.get() || tool.getItem() == Items.NETHERITE_HOE) {
            tool.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            tool.enchant(Enchantments.BLOCK_FORTUNE, 3);
        } else {
            tool.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            tool.enchant(Enchantments.BLOCK_FORTUNE, 3);
        }

        return tool;
    }
}
