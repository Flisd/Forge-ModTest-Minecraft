package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.enchantment.ModEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
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
                        output.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                        output.accept(ModBlocks.RAW_SAPPHIRE_BLOCK.get());

                        output.accept(ModBlocks.SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.NETHER_SAPPHIRE_ORE.get());
                        output.accept(ModBlocks.END_SAPPHIRE_ORE.get());

                        output.accept(ModItems.METAL_DETECTOR.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                        output.accept(ModBlocks.LAUNCH_BLOCK.get());

                        output.accept(ModItems.STRAWBERRY.get());

                        output.accept(ModItems.PINE_CONE.get());

                        output.accept(ModBlocks.SAPPHIRE_STAIRS.get());
                        output.accept(ModBlocks.SAPPHIRE_SLAB.get());
                        output.accept(ModBlocks.SAPPHIRE_BUTTON.get());
                        output.accept(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.SAPPHIRE_FENCE.get());
                        output.accept(ModBlocks.SAPPHIRE_FENCE_GATE.get());
                        output.accept(ModBlocks.SAPPHIRE_WALL.get());
                        output.accept(ModBlocks.SAPPHIRE_DOOR.get());
                        output.accept(ModBlocks.SAPPHIRE_TRAP_DOOR.get());

                        output.accept(ModItems.SAPPHIRE_STAFF.get());

                        output.accept(ModItems.SAPPHIRE_SWORD.get());
                        output.accept(ModItems.SAPPHIRE_PICKAXE.get());
                        output.accept(ModItems.SAPPHIRE_AXE.get());
                        output.accept(ModItems.SAPPHIRE_SHOVEL.get());
                        output.accept(ModItems.SAPPHIRE_HOE.get());

                        output.accept(ModItems.SAPPHIRE_HELMET.get());
                        output.accept(ModItems.SAPPHIRE_CHESTPLATE.get());
                        output.accept(ModItems.SAPPHIRE_LEGGINGS.get());
                        output.accept(ModItems.SAPPHIRE_BOOTS.get());

                        output.accept(ModItems.SPAWNER_DETECTOR.get());
                        output.accept(ModItems.DRILL.get());

                        output.accept(ModItems.IVY_SWORD.get());
                        output.accept(ModItems.TNT_SWORD.get());
                        output.accept(ModItems.POTION_SWORD.get());
                        output.accept(ModItems.LIGHTNING_SWORD.get());

                        output.accept(ModItems.ORE_PICKAXE.get());
                        output.accept(ModBlocks.ENDER_BLOCK.get());

                        output.accept(ModBlocks.ORE_LUCK_BLOCK.get());

                        output.accept(ModBlocks.CAMO_BLOCK.get());

                        output.accept(ModBlocks.DIAMOND_TNT.get());

                        output.accept(ModItems.TOTEM_OF_MAGNET.get());
                        output.accept(ModItems.MAGNET_STAFF.get());

                        output.accept(ModItems.PLACE_STAFF.get());
                        output.accept(ModItems.RADIUS_BALL.get());
                        output.accept(ModItems.DICE.get());
                        output.accept(ModItems.TNT_ORB.get());
                        //output.accept(ModBlocks.DICE_BLOCK.get());
                        // if you want to add an item already in the game
                        // output.accept(Items.BEACON);
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
