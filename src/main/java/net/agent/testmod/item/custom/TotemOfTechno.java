package net.agent.testmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TotemOfTechno extends Item {

    public TotemOfTechno(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Level level = player.level();
            if (!level.isClientSide) {
                for (ItemStack stack : player.getInventory().items) {
                    if (stack.getItem() instanceof TotemOfTechno) {
                        // Remove the totem from the player's inventory
                        stack.shrink(1);
                        // Prevent the player from dropping their items
                        event.setCanceled(true);
                        // Store the player's inventory, offhand, and armor
                        ItemStack[] savedInventory = player.getInventory().items.toArray(new ItemStack[0]);
                        ItemStack savedOffhand = player.getOffhandItem().copy();
                        ItemStack[] savedArmor = player.getInventory().armor.toArray(new ItemStack[0]);
                        // Clear the player's inventory, offhand, and armor
                        player.getInventory().clearContent();
                        player.getInventory().armor.clear();
                        player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                        // Trigger the Totem of Undying effect
                        player.setHealth(1.0F);
                        player.removeAllEffects();
                        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 900, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 100, 1));
                        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0));
                        level.broadcastEntityEvent(player, (byte) 35);
                        // Respawn the player with their items
                        player.setHealth(20.0F);
                        player.setAirSupply(player.getMaxAirSupply());
                        player.setInvulnerable(true);
                        player.teleportTo(player.getX(), player.getY(), player.getZ());
                        player.setInvulnerable(false);
                        // Restore the player's inventory, offhand, and armor
                        for (int i = 0; i < savedInventory.length; i++) {
                            player.getInventory().items.set(i, savedInventory[i]);
                        }
                        player.setItemInHand(InteractionHand.OFF_HAND, savedOffhand);
                        for (int i = 0; i < savedArmor.length; i++) {
                            player.getInventory().armor.set(i, savedArmor[i]);
                        }
                        player.sendSystemMessage(Component.literal("TECHNOBLADE NEVER DIES").withStyle(Style.EMPTY.withColor(0xFFD700)));
                        break;
                    }
                }
            }
        }
    }
}