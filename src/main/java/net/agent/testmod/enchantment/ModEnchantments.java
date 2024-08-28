package net.agent.testmod.enchantment;

import net.agent.testmod.TestMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TestMod.MOD_ID);

    public static RegistryObject<Enchantment> LIGHTING_STRIKER = ENCHANTMENTS.register("lightning_striker",
            ()-> new LighthingStrikerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static RegistryObject<Enchantment> DOUBLE_TROUBLE = ENCHANTMENTS.register("double_trouble",
            ()-> new DoubleTrouble(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static RegistryObject<Enchantment> STRIP_MINING = ENCHANTMENTS.register("strip_mining",
            ()-> new StripMining());

    public static RegistryObject<Enchantment> THERE_GOES_GRAVITY = ENCHANTMENTS.register("there_goes_gravity",
            ()-> new ThereGoesGravity(Enchantment.Rarity.RARE, EnchantmentCategory.ARMOR, new EquipmentSlot[]{EquipmentSlot.CHEST}));

    public static RegistryObject<Enchantment> FIRE_TRAIL = ENCHANTMENTS.register("fire_trail",
            ()-> new FireTrail());

    public static RegistryObject<Enchantment> THUNDER_CLAP = ENCHANTMENTS.register("thunder_clap",
            ()-> new ThunderClap());

    public static RegistryObject<Enchantment> BOW_BOOM = ENCHANTMENTS.register("bow_boom",
            ()-> new BoomBow());

    public static RegistryObject<Enchantment> SHOCKWAVE = ENCHANTMENTS.register("shockwave",
            ()-> new ShockWave());

    public static RegistryObject<Enchantment> QUICK_DRAW = ENCHANTMENTS.register("quick_draw",
            ()-> new QuickDraw());

    public static RegistryObject<Enchantment> REJUVENATION = ENCHANTMENTS.register("rejuvenation",
            ()-> new Rejuvenation());

    public static void register(IEventBus iEventBus){
        ENCHANTMENTS.register(iEventBus);
    }
}
