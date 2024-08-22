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
            ()-> new StripMining(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register(IEventBus iEventBus){
        ENCHANTMENTS.register(iEventBus);
    }
}
