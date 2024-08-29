package net.agent.testmod.command.custom;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.RegisterCommandsEvent;

public class SetHomeCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("sethome")
                .then(Commands.argument("name", StringArgumentType.word())
                        .executes(context -> {
                            ServerPlayer player = context.getSource().getPlayerOrException();
                            String homeName = StringArgumentType.getString(context, "name");
                            BlockPos homePos = player.blockPosition();

                            // Save home location to player's NBT data
                            CompoundTag playerData = player.getPersistentData();
                            CompoundTag homesTag = playerData.getCompound("Homes");
                            CompoundTag homeTag = new CompoundTag();
                            homeTag.putInt("x", homePos.getX());
                            homeTag.putInt("y", homePos.getY());
                            homeTag.putInt("z", homePos.getZ());
                            homesTag.put(homeName, homeTag);
                            playerData.put("Homes", homesTag);

                            return 1;
                        })));
    }
}
