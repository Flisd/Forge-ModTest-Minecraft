package net.agent.testmod.enchantment;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class QuickDrawEventHandler {

    @SubscribeEvent
    public static void onArrowLoose(ArrowLooseEvent event) {
        Player player = event.getEntity();
        ItemStack bow = event.getBow();

        // Check if the bow has the QuickDraw enchantment
        int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.QUICK_DRAW.get(), bow);
        if (enchantmentLevel > 0) {
            // Reduce the draw time based on the enchantment level
            int newCharge = event.getCharge() + (enchantmentLevel * 5);
            event.setCharge(newCharge);
        }
    }
}
