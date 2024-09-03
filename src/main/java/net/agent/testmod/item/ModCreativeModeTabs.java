package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GOD_TAB = CREATIVE_MODE_TABS.register("god_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SAPPHIRE.get(), 1))
                    .title(Component.translatable("creativetab.test_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(new ItemStack(ModItems.SAPPHIRE.get(), 1));
                        output.accept(new ItemStack(ModItems.RAW_SAPPHIRE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_BLOCK.get(), 1));
                        output.accept(new ItemStack(ModBlocks.RAW_SAPPHIRE_BLOCK.get(), 1));

                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_ORE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.NETHER_SAPPHIRE_ORE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.END_SAPPHIRE_ORE.get(), 1));

                        output.accept(new ItemStack(ModItems.METAL_DETECTOR.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SOUND_BLOCK.get(), 1));
                        output.accept(new ItemStack(ModBlocks.LAUNCH_BLOCK.get(), 1));

                        output.accept(new ItemStack(ModItems.STRAWBERRY.get(), 1));

                        output.accept(new ItemStack(ModItems.PINE_CONE.get(), 1));

                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_STAIRS.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_SLAB.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_BUTTON.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_FENCE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_FENCE_GATE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_WALL.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_DOOR.get(), 1));
                        output.accept(new ItemStack(ModBlocks.SAPPHIRE_TRAP_DOOR.get(), 1));

                        output.accept(new ItemStack(ModItems.SAPPHIRE_STAFF.get(), 1));

                        output.accept(new ItemStack(ModItems.SAPPHIRE_SWORD.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_PICKAXE.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_AXE.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_SHOVEL.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_HOE.get(), 1));

                        output.accept(new ItemStack(ModItems.SAPPHIRE_HELMET.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_CHESTPLATE.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_LEGGINGS.get(), 1));
                        output.accept(new ItemStack(ModItems.SAPPHIRE_BOOTS.get(), 1));

                        output.accept(new ItemStack(ModItems.SPAWNER_DETECTOR.get(), 1));
                        output.accept(new ItemStack(ModItems.DRILL.get(), 1));

                        output.accept(new ItemStack(ModItems.IVY_SWORD.get(), 1));
                        output.accept(new ItemStack(ModItems.TNT_SWORD.get(), 1));
                        output.accept(new ItemStack(ModItems.POTION_SWORD.get(), 1));
                        output.accept(new ItemStack(ModItems.LIGHTNING_SWORD.get(), 1));

                        output.accept(new ItemStack(ModItems.ORE_PICKAXE.get(), 1));
                        output.accept(new ItemStack(ModBlocks.ENDER_BLOCK.get(), 1));

                        output.accept(new ItemStack(ModBlocks.ORE_LUCK_BLOCK.get(), 1));

                        output.accept(new ItemStack(ModBlocks.CAMO_BLOCK.get(), 1));

                        output.accept(new ItemStack(ModBlocks.DIAMOND_TNT.get(), 1));

                        output.accept(new ItemStack(ModItems.TOTEM_OF_MAGNET.get(), 1));
                        output.accept(new ItemStack(ModItems.MAGNET_STAFF.get(), 1));

                        output.accept(new ItemStack(ModItems.PLACE_STAFF.get(), 1));
                        output.accept(new ItemStack(ModItems.RADIUS_BALL.get(), 1));
                        output.accept(new ItemStack(ModItems.DICE.get(), 1));
                        output.accept(new ItemStack(ModItems.TNT_ORB.get(), 1));
                        output.accept(new ItemStack(ModItems.FOOD_ORB.get(), 1));
                        output.accept(new ItemStack(ModItems.GOD_ORB.get(), 1));
                        output.accept(new ItemStack(ModItems.PORTAL_ORB.get(), 1));
                        output.accept(new ItemStack(ModItems.STEEL_BALL.get(), 1));
                        output.accept(new ItemStack(ModItems.WATER_LAVA.get(), 1));
                        output.accept(new ItemStack(ModItems.COMPACT_BOTTLE.get(), 1));
                        output.accept(new ItemStack(ModItems.TUNNEL_ORB.get(), 1));
                        output.accept(new ItemStack(ModItems.TOTEM_OF_TECHNO.get(), 1));

//                        output.accept(new ItemStack(ModBlocks.BEACON_POW.get(), 1));
                        //output.accept(new ItemStack(ModBlocks.PROJECTILE_SHOOTER_BLOCK.get(), 1));
                        //output.accept(new ItemStack(ModBlocks.DICE_BLOCK.get(), 1));
                        // if you want to add an item already in the game
                        // output.accept(new ItemStack(Items.BEACON, 1));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
