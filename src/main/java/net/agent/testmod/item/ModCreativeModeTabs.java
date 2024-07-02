package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GOD_TAB = CREATIVE_MODE_TABS.register("god_tab",
            () -> CreativeModeTab.builder().icon( () -> new ItemStack(ModItems.SAPPHIRE.get()))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SAPPHIRE.get());
                        output.accept(ModItems.RAW_SAPPHIRE.get());
                        output.accept(ModBlock.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlock.RAW_SAPPHIRE_BLOCK.get());

                        output.accept(ModBlock.SAPPHIRE_ORE.get());
                        output.accept(ModBlock.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModBlock.NETHER_SAPPHIRE_ORE.get());
                        output.accept(ModBlock.END_SAPPHIRE_ORE.get());

                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModBlock.SOUND_BLOCK.get());
                        output.accept(ModBlock.LAUNCH_BLOCK.get());

                        output.accept(ModItems.STRAWBERRY.get());

                        output.accept(ModItems.PINE_CONE.get());
                        // if you want to add an item already in the game
                        // output.accept(Items.BEACON);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
