package net.agent.testmod.item.custom;

import net.agent.testmod.item.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PlaceStaff extends Item {
    private Block placeBlock = Blocks.DIRT;
    private int radiusBallObjects = 1;
    private static final int MAX_RADIUS = 20;
    private boolean is3D = true; // New property to toggle between 3D and 2D

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
                for (int y = is3D ? -radiusBallObjects : 0; y <= (is3D ? radiusBallObjects : 0); y++) {
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

    public void toggleMode() {
        is3D = !is3D;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal(super.getName(stack).getString() + " (Radius: " + radiusBallObjects + ", Block: " + placeBlock.getName().getString() + ", Mode: " + (is3D ? "3D" : "2D") + ")")
                .withStyle(ChatFormatting.AQUA);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack p_150892_, ItemStack p_150893_, Slot p_150894_, ClickAction p_150895_, Player p_150896_, SlotAccess p_150897_) {
        Player player = p_150896_;
        ItemStack currentStack = p_150892_;
        ItemStack nextStack = p_150893_;

        if (currentStack.getItem() instanceof PlaceStaff && nextStack.getItem() == ModItems.RADIUS_BALL.get()) {
            increaseRadius();
            nextStack.shrink(1);
            return true;
        }
        else if (currentStack.getItem() instanceof PlaceStaff && nextStack.getItem() == Items.REDSTONE) {
            toggleMode();
            nextStack.shrink(1);
            return true;
        } else if (currentStack.getItem() instanceof PlaceStaff && nextStack.getItem() instanceof BlockItem) {
            Block newBlock = ((BlockItem) nextStack.getItem()).getBlock();
            setPlaceBlock(newBlock);
            nextStack.shrink(1);
            return true;
        }

        return super.overrideOtherStackedOnMe(p_150892_, p_150893_, p_150894_, p_150895_, p_150896_, p_150897_);
    }
}
