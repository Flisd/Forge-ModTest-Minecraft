package net.agent.testmod.command.custom;

import net.agent.testmod.TestMod;
import net.agent.testmod.command.custom.GearMeUpMonkeyCommand;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommandRegistration {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        GearMeUpMonkeyCommand.register(event);
        IllegalTotemsCommand.register(event);
        HomeCommand.register(event);
        SetHomeCommand.register(event);
        CraftCommand.register(event);
        EnderChestCommand.register(event);
        AnvilCommand.register(event);

        // WIP
        // PlayerVaultCommand.register(event);

        ClearHomeCommand.register(event);

        ChatGameCommand.register(event);
    }
}
