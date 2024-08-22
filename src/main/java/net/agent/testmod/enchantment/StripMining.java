package net.agent.testmod.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class StripMining extends Enchantment {

    private static final ChatFormatting[] RAINBOW_COLORS = {
            ChatFormatting.RED, ChatFormatting.GOLD, ChatFormatting.YELLOW,
            ChatFormatting.GREEN, ChatFormatting.AQUA, ChatFormatting.BLUE,
            ChatFormatting.LIGHT_PURPLE
    };

    protected StripMining() {
        super(Enchantment.Rarity.RARE, EnchantmentCategory.DIGGER, new EquipmentSlot[] { EquipmentSlot.MAINHAND });
    }

    @Override
    public Component getFullname(int level) {
        String name = "Strip Mining";
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


//    @Override
//    public Component getFullname(int level) {
//        MutableComponent name = Component.translatable(this.getDescriptionId());
//        if (level > 1 || this.getMaxLevel() > 1) {
//            name.append(" ").append(Component.translatable("enchantment.level." + level));
//        }
//        return name.withStyle(ChatFormatting.GOLD); // Change GOLD to your preferred color
//    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.getItem() == Items.DIAMOND_PICKAXE || super.canApplyAtEnchantingTable(stack);
    }
}
