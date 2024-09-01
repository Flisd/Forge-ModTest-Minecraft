package net.agent.testmod.command.custom;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class SeeCustomCraftCommand {
    private static final int ITEMS_PER_PAGE = 54; // 6 rows * 9 slots per row

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("customCraft")
                .then(Commands.argument("page", StringArgumentType.word())
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            int page = Integer.parseInt(StringArgumentType.getString(context, "page"));
                            openCustomCraftMenu(player, page);
                            return 1;
                        })));
    }

    private static void openCustomCraftMenu(ServerPlayer player, int page) {
        player.openMenu(new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Custom Crafting Recipes");
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {
                SimpleContainer customCraftInventory = new SimpleContainer(ITEMS_PER_PAGE);
                // Add custom items to the inventory for the current page
                addCustomItemsToInventory(customCraftInventory, page);

                return ChestMenu.sixRows(id, playerInventory, customCraftInventory);
            }
        });
    }

    private static void addCustomItemsToInventory(SimpleContainer inventory, int page) {
        // Add custom items to the inventory based on the page number
        // Example: inventory.setItem(slot, new ItemStack(ModItems.CUSTOM_ITEM.get()));

        // Add "Back" and "Next" buttons
        if (page > 1) {
            ItemStack backButton = new ItemStack(Items.ARROW);
            backButton.setHoverName(Component.literal("Back").withStyle(style -> style.withColor(0xFF0000))); // Red color
            inventory.setItem(45, backButton); // Back button
        }
        ItemStack nextButton = new ItemStack(Items.ARROW);
        nextButton.setHoverName(Component.literal("Next").withStyle(style -> style.withColor(0xFF0000))); // Red color
        inventory.setItem(53, nextButton); // Next button
    }

    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.isEmpty()) {
            // Handle the interaction with an empty hand
            // For example, open the custom crafting menu
            if (player instanceof ServerPlayer) {
                openCustomCraftMenu((ServerPlayer) player, 1); // Open the first page by default
            }
        }
    }

    @SubscribeEvent
    public static void onMenuClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.isEmpty() && player.containerMenu instanceof ChestMenu) {
            ChestMenu menu = (ChestMenu) player.containerMenu;
            int slot = event.getHand().ordinal();

            if (slot == 45) {
                // Handle "Back" button click
                openCustomCraftMenu((ServerPlayer) player, getCurrentPage(menu) - 1);
            } else if (slot == 53) {
                // Handle "Next" button click
                openCustomCraftMenu((ServerPlayer) player, getCurrentPage(menu) + 1);
            }
        }
    }

    private static int getCurrentPage(ChestMenu menu) {
        // Retrieve the current page from the menu
        // This is a placeholder implementation; you may need to store the current page in the player's NBT data or another way
        return 1;
    }
}
