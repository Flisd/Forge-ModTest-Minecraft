package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.custom.FuelItem;
import net.agent.testmod.item.custom.*;
import net.agent.testmod.sound.ModSounds;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> SPAWNER_DETECTOR = ITEMS.register("spawner_detector",
            () -> new SpawnerDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoods.STRAWBERRY)));

    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            () -> new FuelItem(new Item.Properties(), 400));

    public static final RegistryObject<Item> SAPPHIRE_STAFF = ITEMS.register("sapphire_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> SAPPHIRE_SWORD = ITEMS.register("sapphire_sword",
            () -> new SwordItem(ModToolTiers.SAPPHIRE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_PICKAXE = ITEMS.register("sapphire_pickaxe",
            () -> new PickaxeItem(ModToolTiers.SAPPHIRE, 1, 2, new Item.Properties()));

    public static final RegistryObject<Item> DRILL = ITEMS.register("drill",
            () -> new DrillToolItem(1, 2, ModToolTiers.SAPPHIRE, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties().durability(2000)));

    public static final RegistryObject<Item> SAPPHIRE_AXE = ITEMS.register("sapphire_axe",
            () -> new AxeItem(ModToolTiers.SAPPHIRE, 7, 1.5f, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_SHOVEL = ITEMS.register("sapphire_shovel",
            () -> new ShovelItem(ModToolTiers.SAPPHIRE, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_HOE = ITEMS.register("sapphire_hoe",
            () -> new HoeItem(ModToolTiers.SAPPHIRE, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_HELMET = ITEMS.register("sapphire_helmet",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_CHESTPLATE = ITEMS.register("sapphire_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_LEGGINGS = ITEMS.register("sapphire_leggings",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> SAPPHIRE_BOOTS = ITEMS.register("sapphire_boots",
            () -> new ArmorItem(ModArmorMaterials.SAPPHIRE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> IVY_SWORD = ITEMS.register("ivy_sword",
            () -> new PosionSwordItem(Tiers.NETHERITE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> TNT_SWORD = ITEMS.register("tnt_sword",
            () -> new ExplosiveSwordItem(Tiers.NETHERITE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> POTION_SWORD = ITEMS.register("potion_sword",
            () -> new PotionSwordItem(Tiers.NETHERITE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> LIGHTNING_SWORD = ITEMS.register("lighting_sword",
            () -> new LightingSwordItem(Tiers.NETHERITE, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> ORE_PICKAXE = ITEMS.register("ore_pickaxe",
            () -> new OreDestroyer(1, 2, Tiers.DIAMOND, BlockTags.MINEABLE_WITH_PICKAXE, new Item.Properties().durability(101)));

    public static final RegistryObject<Item> TOTEM_OF_MAGNET = ITEMS.register("totem_of_magnet",
            () -> new TotemOfMagnet(new Item.Properties().durability(60)));

    public static final RegistryObject<Item> MAGNET_STAFF = ITEMS.register("magnet_staff",
            () -> new MagnetStaff(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> RADIUS_BALL = ITEMS.register("radius_ball",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> PLACE_STAFF = ITEMS.register("place_staff",
            () -> new PlaceStaff(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> DICE = ITEMS.register("dice",
            () -> new DiceItem(new Item.Properties()));

    public static final RegistryObject<Item> TNT_ORB = ITEMS.register("tnt_orb",
            () -> new TntOrbItem(new Item.Properties()));

    public static final RegistryObject<Item> FOOD_ORB = ITEMS.register("food_orb",
            () -> new FoodOrbItem(new Item.Properties()));

    public static final RegistryObject<Item> GOD_ORB = ITEMS.register("god_orb",
            () -> new GodOrbItem(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> PORTAL_ORB = ITEMS.register("portal_orb",
            () -> new PortalOrbItem(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_BALL = ITEMS.register("steel_ball",
            () -> new SteelBall(new Item.Properties()));

    public static final RegistryObject<Item> WATER_LAVA = ITEMS.register("water_lava",
            () -> new WaterLavaOrb(new Item.Properties()));

    public static final RegistryObject<Item> COMPACT_BOTTLE = ITEMS.register("compact_enchanting_bottle",
            () -> new CompactBottleItem(new Item.Properties()));

    public static final RegistryObject<Item> TUNNEL_ORB = ITEMS.register("tunnel_orb",
            () -> new TunnelOrbItem(new Item.Properties()));

    public static final RegistryObject<Item> TOTEM_OF_TECHNO = ITEMS.register("totem_of_techno",
            () -> new TotemOfTechno(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> WIDE_PUTIN_DISK = ITEMS.register("wide_putin_disk",
            () -> new RecordItem(6, ModSounds.WIDE_PUTIN, new Item.Properties().stacksTo(1),1400));

    public static final RegistryObject<Item> DEV_DISK_ITEM = ITEMS.register("dev_disk_item",
            () -> new RecordItem(6, ModSounds.DEV_SOUND, new Item.Properties().stacksTo(1),1800));

    public static final RegistryObject<Item> MAGIC_DUST = ITEMS.register("magic_dust",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> TOTEM_OF_FIRE = ITEMS.register("totem_of_fire",
            () -> new TotemOfFire(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> TOTEM_OF_BEAST = ITEMS.register("totem_of_beast",
            () -> new TotemOfTheBeastmaster(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CHANCE_ORB = ITEMS.register("chance_orb",
            () -> new ChanceOrb(new Item.Properties().food(ModFoods.CHANCE_ORB_FOOD)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}