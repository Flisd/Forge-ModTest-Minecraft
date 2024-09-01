package net.agent.testmod.command.custom;

import net.agent.testmod.item.ModItems;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.jetbrains.annotations.Nullable;

public class PlayerVaultCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("pv")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    player.openMenu(new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("Player Vault");
                        }

                        @Nullable
                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {
                            return ChestMenu.sixRows(id, playerInventory, player.getEnderChestInventory());
                        }
                    });
                    player.addItem(new ItemStack(ModItems.COMPACT_BOTTLE.get(), 64));
                    return 1;
                }));

        event.getDispatcher().register(Commands.literal("playervault")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    player.openMenu(new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("Player Vault");
                        }

                        @Nullable
                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {
                            return ChestMenu.sixRows(id, playerInventory, player.getEnderChestInventory());
                        }
                    });
                    return 1;
                }));
    }
}
