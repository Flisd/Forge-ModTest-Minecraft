package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.agent.testmod.item.custom.PlaceStaff;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraft.world.InteractionHand;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandler {

    @SubscribeEvent
    public static void onInventoryChanged(PlayerEvent.ItemPickupEvent event) {
        Player player = event.getEntity();
        for (int i = 0; i < player.getInventory().getContainerSize() - 1; i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            ItemStack nextStack = player.getInventory().getItem(i + 1);

            if (currentStack.getItem() instanceof PlaceStaff && nextStack.getItem() == ModItems.RADIUS_BALL.get()) {
                PlaceStaff staff = (PlaceStaff) currentStack.getItem();
                staff.increaseRadius();
                player.getInventory().removeItem(nextStack);
                break;
            } else if (currentStack.getItem() instanceof PlaceStaff && nextStack.getItem() instanceof BlockItem) {
                PlaceStaff staff = (PlaceStaff) currentStack.getItem();
                Block newBlock = ((BlockItem) nextStack.getItem()).getBlock();
                staff.setPlaceBlock(newBlock);
                player.getInventory().removeItem(nextStack);
                break;
            }
        }
    }

    @SubscribeEvent
    public static void onItemRightClick(RightClickItem event) {
        Player player = event.getEntity();
        InteractionHand hand = event.getHand();
        ItemStack heldItem = player.getItemInHand(hand); // Item in the player's hand
        ItemStack offHandItem = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND); // Item in the other hand

        // Check if both items are not empty
        if (!heldItem.isEmpty() && !offHandItem.isEmpty()) {
            // Example logic: if a PlaceStaff is held and the offhand item is a Radius Ball
            if (heldItem.getItem() instanceof PlaceStaff && offHandItem.getItem() == ModItems.RADIUS_BALL.get()) {
                PlaceStaff staff = (PlaceStaff) heldItem.getItem();
                staff.increaseRadius();
                player.setItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, ItemStack.EMPTY); // Clear the item in the offhand
                event.setCanceled(true); // Cancel further event processing
            }
            // Example logic: if a PlaceStaff is held and the offhand item is a BlockItem
            else if (heldItem.getItem() instanceof PlaceStaff && offHandItem.getItem() instanceof BlockItem) {
                PlaceStaff staff = (PlaceStaff) heldItem.getItem();
                Block newBlock = ((BlockItem) offHandItem.getItem()).getBlock();
                staff.setPlaceBlock(newBlock);
                player.setItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, ItemStack.EMPTY); // Clear the item in the offhand
                event.setCanceled(true); // Cancel further event processing
            }
        }
    }
}
