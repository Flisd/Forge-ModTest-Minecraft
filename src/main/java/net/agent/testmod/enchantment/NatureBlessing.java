package net.agent.testmod.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class NatureBlessing extends Enchantment {

    private static final ChatFormatting[] RAINBOW_COLORS = {
            ChatFormatting.GREEN, ChatFormatting.DARK_GREEN
    };

    protected NatureBlessing() {
        super(Rarity.RARE, EnchantmentCategory.BREAKABLE, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public Component getFullname(int level) {
        String name = "Nature's Blessing";
        MutableComponent rainbowName = Component.literal("");

        for (int i = 0; i < name.length(); i++) {
            char letter = name.charAt(i);
            ChatFormatting color = RAINBOW_COLORS[(i + (int) (System.currentTimeMillis() / 100)) % RAINBOW_COLORS.length];
            rainbowName.append(Component.literal(String.valueOf(letter)).withStyle(color));
        }

        if (level > 1 || this.getMaxLevel() > 1) {
            rainbowName.append(" ").append(Component.translatable("enchantment.level." + level));
        }

        return rainbowName;
    }

    @Override
    public int getMinCost(int level) {
        // This sets the minimum level required for the enchantment to appear.
        return 30 + level * 20;
    }

    @Override
    public int getMaxCost(int level) {
        // This sets the maximum level required for the enchantment to appear.
        return this.getMinCost(level) + 30;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == Items.STICK;
    }
}
