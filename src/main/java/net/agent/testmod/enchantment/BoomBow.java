package net.agent.testmod.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class BoomBow extends Enchantment {

    private static final ChatFormatting[] COLORS = {
            ChatFormatting.RED, ChatFormatting.GOLD, ChatFormatting.YELLOW
    };

    public BoomBow() {
        super(Rarity.RARE, EnchantmentCategory.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == Items.BOW || stack.getItem() == Items.CROSSBOW || super.canApplyAtEnchantingTable(stack);
    }

    @Override
    public Component getFullname(int level) {
        String name = "Boom Bow";
        MutableComponent coloredName = Component.literal("");

        long time = System.currentTimeMillis() / 100; // Adjust the speed of color change
        for (int i = 0; i < name.length(); i++) {
            char letter = name.charAt(i);
            ChatFormatting color = COLORS[(i + (int) time) % COLORS.length];
            coloredName.append(Component.literal(String.valueOf(letter)).withStyle(color));
        }

        if (level > 1 || this.getMaxLevel() > 1) {
            coloredName.append(" ").append(Component.translatable("enchantment.level." + level));
        }

        return coloredName;
    }
}
