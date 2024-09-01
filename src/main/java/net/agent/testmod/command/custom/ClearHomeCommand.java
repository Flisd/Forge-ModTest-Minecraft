package net.agent.testmod.command.custom;

import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.RegisterCommandsEvent;

public class ClearHomeCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("clearHome")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();

                    CompoundTag playerData = player.getPersistentData();
                    playerData.remove("Homes");

                    return 1;
                }));
    }
}