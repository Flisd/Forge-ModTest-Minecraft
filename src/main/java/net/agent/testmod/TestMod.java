package net.agent.testmod;

import com.mojang.logging.LogUtils;
import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.block.custom.VortexBlock;
import net.agent.testmod.block.entity.ModBlockEntities;
import net.agent.testmod.block.entity.renderer.BeaconSquareBlockEntityRenderer;
import net.agent.testmod.block.entity.renderer.HologramBlockEntityRenderer;
import net.agent.testmod.block.entity.renderer.OreGenBlockEntity;
import net.agent.testmod.effect.ModEffects;
import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.entity.ModEntities;
import net.agent.testmod.event.RandomSoundEventHandler;
import net.agent.testmod.item.ModCreativeModeTabs;
import net.agent.testmod.item.ModItems;
import net.agent.testmod.particle.ModParticles;
import net.agent.testmod.sound.ModSounds;
import net.agent.testmod.villager.ModVillagers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
// https://www.youtube.com/watch?v=C_VO6tD6Y1g&list=PLKGarocXCE1H9Y21-pxjt5Pt8bW14twa-&index=3 playlist
@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final String MOD_ID = "testmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public TestMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        ModEnchantments.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModSounds.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModEffects.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(VortexBlock.class);

        ModParticles.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(RandomSoundEventHandler.class);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.OP_BLOCKS) {
            event.accept(ModItems.SAPPHIRE);
            event.accept(ModItems.RAW_SAPPHIRE);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            EntityRenderers.register(ModEntities.DICE_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.TNT_ORB_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.FOOD_ORB_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.GOD_ORB_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.PORTAL_ORB_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.STEEL_BALL_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.WATER_LAVA_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.COMPACT_BOTTLE_PROJECTILE.get(), ThrownItemRenderer::new);
            EntityRenderers.register(ModEntities.TUNNEL_PROJECTILE.get(), ThrownItemRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.BEACON_SQUARE.get(), BeaconSquareBlockEntityRenderer::new);
            BlockEntityRenderers.register(ModBlockEntities.HOLOGRAM_BLOCK_ENTITY.get(), HologramBlockEntityRenderer::new);
        }
    }
}
