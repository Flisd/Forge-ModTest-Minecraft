package net.agent.testmod.command.custom;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.RegisterCommandsEvent;

public class HomeCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("home")
                .then(Commands.argument("name", StringArgumentType.word())
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            String homeName = StringArgumentType.getString(context, "name");

                            // Retrieve home location from player's NBT data
                            CompoundTag playerData = player.getPersistentData();
                            CompoundTag homesTag = playerData.getCompound("Homes");
                            if (homesTag.contains(homeName)) {
                                CompoundTag homeTag = homesTag.getCompound(homeName);
                                int x = homeTag.getInt("x");
                                int y = homeTag.getInt("y");
                                int z = homeTag.getInt("z");
                                BlockPos homePos = new BlockPos(x, y, z);

                                // Teleport player to home location
                                player.teleportTo(homePos.getX(), homePos.getY(), homePos.getZ());
                            }

                            return 1;
                        })));
    }
}
