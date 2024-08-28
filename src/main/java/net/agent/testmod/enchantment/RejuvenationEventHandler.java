package net.agent.testmod.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class RejuvenationEventHandler {

    private static final Random random = new Random();

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack tool = player.getMainHandItem();

        // Check if the tool has the Rejuvenation enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.REJUVENATION.get(), tool) > 0) {
            if (random.nextInt(100) < 5) { // 5% chance
                tool.setDamageValue(tool.getDamageValue() - 1); // Repair 1 durability
            }
        }
    }
}
