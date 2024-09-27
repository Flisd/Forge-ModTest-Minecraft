package net.agent.testmod.command.custom;

import com.google.common.collect.Lists;
import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameType;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.List;

public class IllegalTotemsCommand {
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("IllegalTot")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    if (player.gameMode.getGameModeForPlayer() == GameType.CREATIVE) {
                        player.addItem(new ItemStack(Items.TOTEM_OF_UNDYING, 75));

                        return 1;
                    } else {
                        player.sendSystemMessage((Component.literal("You don't have permission")));
                        return 0;
                    }
                }));
    }
}