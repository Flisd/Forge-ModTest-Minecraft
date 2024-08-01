package net.agent.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class RandomOreBlockGenBlock extends Block {
    public List<Block> blockList;

    public RandomOreBlockGenBlock(Properties p_49795_) {
        super(p_49795_);
        blockList = new ArrayList<>();
        blockList.add(Blocks.NETHERITE_BLOCK);
        blockList.add(Blocks.COPPER_BLOCK);
        blockList.add(Blocks.IRON_BLOCK);
        blockList.add(Blocks.GOLD_BLOCK);
        blockList.add(Blocks.DIAMOND_BLOCK);
        blockList.add(Blocks.EMERALD_BLOCK);
        blockList.add(Blocks.LAPIS_BLOCK);
        blockList.add(Blocks.REDSTONE_BLOCK);
        blockList.add(Blocks.COAL_BLOCK);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        Random random = new Random();
        level.destroyBlock(blockPos, true);
        Block randomBlock = blockList.get(random.nextInt(blockList.size()));
        level.setBlock(blockPos, randomBlock.defaultBlockState(), 3);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(Component.literal("like a note block, only different..."));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }
}
