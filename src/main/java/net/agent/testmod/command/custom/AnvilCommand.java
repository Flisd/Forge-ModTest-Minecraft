package net.agent.testmod.command.custom;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraftforge.event.RegisterCommandsEvent;
import org.jetbrains.annotations.Nullable;

public class AnvilCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("anvil")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    player.openMenu(new MenuProvider() {
                        @Override
                        public Component getDisplayName() {
                            return Component.literal("Anvil");
                        }

                        @Nullable
                        @Override
                        public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player playerEntity) {
                            return new AnvilMenu(id, playerInventory);
                        }
                    });
                    return 1;
                }));
    }
}
