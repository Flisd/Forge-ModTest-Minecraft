package net.agent.testmod.entity;

import net.agent.testmod.TestMod;

import net.agent.testmod.entity.custom.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TestMod.MOD_ID);

    public static final RegistryObject<EntityType<DiceProjectileEntity>> DICE_PROJECTILE =
            ENTITIES.register("dice_projectile", () -> EntityType.Builder.<DiceProjectileEntity>of(DiceProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("dice_projectile"));

    public static final RegistryObject<EntityType<TntOrbProjectileEntity>> TNT_ORB_PROJECTILE =
            ENTITIES.register("tnt_orb_projectile", () -> EntityType.Builder.<TntOrbProjectileEntity>of(TntOrbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("tnt_orb_projectile"));

    public static final RegistryObject<EntityType<FoodOrbProjectileEntity>> FOOD_ORB_PROJECTILE =
            ENTITIES.register("food_orb_projectile", () -> EntityType.Builder.<FoodOrbProjectileEntity>of(FoodOrbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("food_orb_projectile"));

    public static final RegistryObject<EntityType<GodOrbProjectileEntity>> GOD_ORB_PROJECTILE =
            ENTITIES.register("god_orb_projectile", () -> EntityType.Builder.<GodOrbProjectileEntity>of(GodOrbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("god_orb_projectile"));

    public static final RegistryObject<EntityType<PortalOrbProjectileEntity>> PORTAL_ORB_PROJECTILE =
            ENTITIES.register("portal_orb_projectile", () -> EntityType.Builder.<PortalOrbProjectileEntity>of(PortalOrbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("portal_orb_projectile"));

    public static final RegistryObject<EntityType<SteelBallProjectileEntity>> STEEL_BALL_PROJECTILE =
            ENTITIES.register("steel_ball_projectile", () -> EntityType.Builder.<SteelBallProjectileEntity>of(SteelBallProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("steel_ball_projectile"));

    public static final RegistryObject<EntityType<WaterLavaProjectileEntity>> WATER_LAVA_PROJECTILE =
            ENTITIES.register("water_lava_projectile", () -> EntityType.Builder.<WaterLavaProjectileEntity>of(WaterLavaProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("water_lava_projectile"));

    public static final RegistryObject<EntityType<CompactBottleProjectileEntity>> COMPACT_BOTTLE_PROJECTILE =
            ENTITIES.register("compact_enchanting_bottle_pro", () -> EntityType.Builder.<CompactBottleProjectileEntity>of(CompactBottleProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("compact_enchanting_bottle_pro"));

    public static final RegistryObject<EntityType<TunnelOrbProjectileEntity>> TUNNEL_PROJECTILE =
            ENTITIES.register("tunnel_projectile", () -> EntityType.Builder.<TunnelOrbProjectileEntity>of(TunnelOrbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("tunnel_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
