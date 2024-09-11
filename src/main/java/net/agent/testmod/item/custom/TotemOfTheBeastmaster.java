package net.agent.testmod.item.custom;

import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TotemOfTheBeastmaster extends Item {

    public TotemOfTheBeastmaster(Properties properties) {
        super(properties);
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Level level = player.level();
            if (!level.isClientSide) {
                ItemStack mainHandItem = player.getItemInHand(InteractionHand.MAIN_HAND);
                ItemStack offHandItem = player.getItemInHand(InteractionHand.OFF_HAND);

                if (mainHandItem.getItem() instanceof TotemOfTheBeastmaster || offHandItem.getItem() instanceof TotemOfTheBeastmaster) {
                    // Remove the totem from the player's inventory
                    if (mainHandItem.getItem() instanceof TotemOfTheBeastmaster) {
                        mainHandItem.shrink(1);
                    } else {
                        offHandItem.shrink(1);
                    }
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
                    // Spawn friendly zombies
                    for (int i = 0; i < 5; i++) {
                        spawnFriendlyZombie(level, player);
                    }
                }
            }
        }
    }

    private static void spawnFriendlyZombie(Level level, Player player) {
        Zombie zombie = new Zombie(level);
        zombie.setPos(player.getX(), player.getY(), player.getZ());
        zombie.setCustomName(Component.literal("Friendly Zombie").setStyle(Style.EMPTY.withColor(0x00FF00)));
        zombie.setCustomNameVisible(true);

        // helmet
        ItemStack helmet = new ItemStack(ModItems.SAPPHIRE_HELMET.get());
        helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
        helmet.enchant(Enchantments.MENDING, 1);
        helmet.enchant(Enchantments.UNBREAKING, 3);
        helmet.enchant(ModEnchantments.REJUVENATION.get(), 1);

        // chest
        ItemStack chestplate = new ItemStack(ModItems.SAPPHIRE_CHESTPLATE.get());
        chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
        chestplate.enchant(Enchantments.MENDING, 1);
        chestplate.enchant(Enchantments.UNBREAKING, 3);
        chestplate.enchant(ModEnchantments.REJUVENATION.get(), 1);

        // leg
        ItemStack leggings = new ItemStack(ModItems.SAPPHIRE_LEGGINGS.get());
        leggings.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
        leggings.enchant(Enchantments.MENDING, 1);
        leggings.enchant(Enchantments.UNBREAKING, 3);
        leggings.enchant(ModEnchantments.REJUVENATION.get(), 1);

        // boots
        ItemStack boots = new ItemStack(ModItems.SAPPHIRE_BOOTS.get());
        boots.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
        boots.enchant(Enchantments.MENDING, 1);
        boots.enchant(Enchantments.UNBREAKING, 3);
        boots.enchant(ModEnchantments.REJUVENATION.get(), 1);
        boots.enchant(ModEnchantments.THUNDER_CLAP.get(), 1);
        boots.enchant(ModEnchantments.SPEED_BOOTS.get(), 1);

        ItemStack sword = new ItemStack(ModItems.LIGHTNING_SWORD.get());
        sword.enchant(Enchantments.SHARPNESS, 5);
        sword.enchant(Enchantments.MENDING, 1);
        sword.enchant(Enchantments.KNOCKBACK, 1);
        sword.enchant(Enchantments.FIRE_ASPECT, 2);
        sword.enchant(Enchantments.UNBREAKING, 3);
        sword.enchant(ModEnchantments.REJUVENATION.get(), 1);
        sword.enchant(ModEnchantments.THERE_GOES_GRAVITY.get(), 1);
        sword.enchant(ModEnchantments.LIGHTING_STRIKER.get(), 3);

        zombie.setItemSlot(EquipmentSlot.HEAD, helmet);
        zombie.setItemSlot(EquipmentSlot.CHEST, chestplate);
        zombie.setItemSlot(EquipmentSlot.LEGS, leggings);
        zombie.setItemSlot(EquipmentSlot.FEET, boots);
        zombie.setItemSlot(EquipmentSlot.MAINHAND, sword);

        level.addFreshEntity(zombie);


        // Custom goal to prevent zombies from attacking the player
        zombie.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(zombie, Mob.class, 10, true, false, (entity) -> entity.getMobType() != MobType.UNDEAD && !(entity instanceof Player)));
        zombie.targetSelector.addGoal(1, new HurtByTargetGoal(zombie).setAlertOthers(Zombie.class));

        // Custom goal to prevent zombies from attacking the player
        zombie.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(zombie, Player.class, 10, true, false, (entity) -> false));

    }
}
