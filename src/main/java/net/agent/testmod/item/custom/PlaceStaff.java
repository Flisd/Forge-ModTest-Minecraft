package net.agent.testmod.item.custom;

import net.agent.testmod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PlaceStaff extends Item {
    private Block placeBlock = Blocks.DIRT;
    private int radiusBallObjects = 1;
    private static final int MAX_RADIUS = 10;

    public PlaceStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        InteractionHand hand = context.getHand();
        ItemStack stack = player.getItemInHand(hand);
        ItemStack offhandStack = player.getOffhandItem();

        if (offhandStack.getItem() == ModItems.RADIUS_BALL.get()) {
            increaseRadius();
            offhandStack.shrink(1);
            return InteractionResult.SUCCESS;
        } else if (offhandStack.getItem() instanceof BlockItem) {
            Block newBlock = ((BlockItem) offhandStack.getItem()).getBlock();
            setPlaceBlock(newBlock);
            offhandStack.shrink(1);
            return InteractionResult.SUCCESS;
        } else {
            // Place blocks in a radius around the clicked position
            for (int x = -radiusBallObjects; x <= radiusBallObjects; x++) {
                for (int y = -radiusBallObjects; y <= radiusBallObjects; y++) {
                    for (int z = -radiusBallObjects; z <= radiusBallObjects; z++) {
                        if (Math.sqrt(x * x + y * y + z * z) <= radiusBallObjects) {
                            level.setBlock(context.getClickedPos().offset(x, y, z), placeBlock.defaultBlockState(), 3);
                        }
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
    }

    public void increaseRadius() {
        if (radiusBallObjects < MAX_RADIUS) {
            radiusBallObjects++;
        }
    }

    public void setPlaceBlock(Block block) {
        this.placeBlock = block;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal(super.getName(stack).getString() + " (Radius: " + radiusBallObjects + ", Block: " + placeBlock.getName().getString() + ")")
                .withStyle(ChatFormatting.AQUA);
    }
}
