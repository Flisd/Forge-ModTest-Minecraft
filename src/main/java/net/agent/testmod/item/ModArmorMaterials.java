package net.agent.testmod.item;

import net.agent.testmod.TestMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    SAPPHIRE("sapphire", 26,new int[]{5,7,5,4},25, SoundEvents.ARMOR_EQUIP_GOLD, 1f, 0f,()->Ingredient.of(ModItems.SAPPHIRE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equiqSound;
    private final float toughness;
    private final float knockbackResistace;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11,16,16,13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistace, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equiqSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistace = knockbackResistace;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        int index = type.ordinal();
        if (index >= 0 && index < BASE_DURABILITY.length) {
            return BASE_DURABILITY[index] * this.durabilityMultiplier;
        } else {
            // Handle the case where the index is out of bounds
            return 0;
        }
    }


    @Override
    public int getDefenseForType(ArmorItem.Type p_267168_) {
        return this.protectionAmounts[p_267168_.ordinal()];
    }


    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equiqSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return TestMod.MOD_ID+":"+this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistace;
    }
}
