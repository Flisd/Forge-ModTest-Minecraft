package net.agent.testmod.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class ThereGoesGravity extends Enchantment {

    protected ThereGoesGravity(Rarity rarity, EnchantmentCategory category, EquipmentSlot[] slots) {
        super(rarity, category, slots);
    }

    @Override
    public Component getFullname(int level) {
        MutableComponent name = Component.translatable(this.getDescriptionId());
        if (level > 1 || this.getMaxLevel() > 1) {
            name.append(" ").append(Component.translatable("enchantment.level." + level));
        }
        return name.withStyle(ChatFormatting.AQUA);
    }


    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == Items.DIAMOND_SWORD || super.canApplyAtEnchantingTable(stack);
    }
}
