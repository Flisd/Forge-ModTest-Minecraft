package net.agent.testmod.command.custom;

import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import com.google.common.collect.Lists;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber
public class GearMeUpMonkeyCommand {

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("GearMeUpMonkey")
                .executes(context -> {
                    ServerPlayer player = context.getSource().getPlayerOrException();
                    // Sapphire Helmet
                    ItemStack helmet = new ItemStack(ModItems.SAPPHIRE_HELMET.get());
                    helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
                    helmet.enchant(Enchantments.MENDING, 1);
                    helmet.enchant(Enchantments.UNBREAKING, 3);
                    helmet.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    addItemOrDrop(player, helmet);

                    // Sapphire Chestplate
                    ItemStack chestplate = new ItemStack(ModItems.SAPPHIRE_CHESTPLATE.get());
                    chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
                    chestplate.enchant(Enchantments.MENDING, 1);
                    chestplate.enchant(Enchantments.UNBREAKING, 3);
                    chestplate.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    addItemOrDrop(player, chestplate);

                    // Sapphire Leggings
                    ItemStack leggings = new ItemStack(ModItems.SAPPHIRE_LEGGINGS.get());
                    leggings.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
                    leggings.enchant(Enchantments.MENDING, 1);
                    leggings.enchant(Enchantments.UNBREAKING, 3);
                    leggings.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    addItemOrDrop(player, leggings);

                    // Sapphire Boots
                    ItemStack boots = new ItemStack(ModItems.SAPPHIRE_BOOTS.get());
                    boots.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
                    boots.enchant(Enchantments.MENDING, 1);
                    boots.enchant(Enchantments.UNBREAKING, 3);
                    boots.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    boots.enchant(ModEnchantments.THUNDER_CLAP.get(), 1);
                    boots.enchant(ModEnchantments.SPEED_BOOTS.get(), 1);
                    addItemOrDrop(player, boots);

                    // Potion Sword
                    ItemStack sword = new ItemStack(ModItems.POTION_SWORD.get());
                    sword.enchant(Enchantments.SHARPNESS, 5);
                    sword.enchant(Enchantments.MENDING, 1);
                    sword.enchant(Enchantments.KNOCKBACK, 1);
                    sword.enchant(Enchantments.FIRE_ASPECT, 2);
                    sword.enchant(Enchantments.UNBREAKING, 3);
                    sword.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    sword.enchant(ModEnchantments.THERE_GOES_GRAVITY.get(), 1);
                    sword.enchant(ModEnchantments.LIGHTING_STRIKER.get(), 3);
                    addItemOrDrop(player, sword);

                    // Sapphire Axe
                    ItemStack axe = new ItemStack(ModItems.SAPPHIRE_AXE.get());
                    axe.enchant(Enchantments.SHARPNESS, 5);
                    axe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
                    axe.enchant(Enchantments.MENDING, 1);
                    axe.enchant(Enchantments.UNBREAKING, 3);
                    axe.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    axe.enchant(ModEnchantments.TREE_BYE.get(), 1);
                    axe.enchant(ModEnchantments.SHOCKWAVE.get(), 1);
                    addItemOrDrop(player, axe);

                    // Sapphire Pickaxe
                    ItemStack pickaxe = new ItemStack(ModItems.SAPPHIRE_PICKAXE.get());
                    pickaxe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
                    pickaxe.enchant(Enchantments.MENDING, 1);
                    pickaxe.enchant(Enchantments.UNBREAKING, 3);
                    pickaxe.enchant(Enchantments.BLOCK_FORTUNE, 3);
                    pickaxe.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    pickaxe.enchant(ModEnchantments.STRIP_MINING.get(), 1);
                    addItemOrDrop(player, pickaxe);

                    // Sapphire Shovel
                    ItemStack shovel = new ItemStack(ModItems.SAPPHIRE_SHOVEL.get());
                    shovel.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
                    shovel.enchant(Enchantments.MENDING, 1);
                    shovel.enchant(Enchantments.UNBREAKING, 3);
                    shovel.enchant(Enchantments.BLOCK_FORTUNE, 3);
                    shovel.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    shovel.enchant(ModEnchantments.DOUBLE_TROUBLE.get(), 1);
                    addItemOrDrop(player, shovel);

                    // 2 stacks of food
                    addItemOrDrop(player, new ItemStack(ModItems.STRAWBERRY.get(), 128));

                    // Bow
                    ItemStack bow = new ItemStack(Items.BOW);
                    bow.enchant(Enchantments.POWER_ARROWS, 5);
                    bow.enchant(Enchantments.INFINITY_ARROWS, 1);
                    bow.enchant(Enchantments.UNBREAKING, 3);
                    bow.enchant(Enchantments.PUNCH_ARROWS, 2);
                    bow.enchant(Enchantments.FLAMING_ARROWS, 1);
                    bow.enchant(ModEnchantments.BOW_BOOM.get(), 2);
                    bow.enchant(ModEnchantments.ELEMENT_SHOT.get(), 1);
                    bow.enchant(ModEnchantments.FLY.get(), 1);
                    bow.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    addItemOrDrop(player, bow);

                    // Crossbow
                    ItemStack crossbow = new ItemStack(Items.CROSSBOW);
                    crossbow.enchant(Enchantments.QUICK_CHARGE, 3);
                    crossbow.enchant(Enchantments.MENDING, 1);
                    crossbow.enchant(Enchantments.UNBREAKING, 3);
                    crossbow.enchant(Enchantments.MULTISHOT, 1);
                    crossbow.enchant(ModEnchantments.BOW_BOOM.get(), 2);
                    crossbow.enchant(ModEnchantments.ELEMENT_SHOT.get(), 1);
                    crossbow.enchant(ModEnchantments.FLY.get(), 1);
                    crossbow.enchant(ModEnchantments.REJUVENATION.get(), 1);
                    addItemOrDrop(player, crossbow);

                    // 1 stack of Arrows
                    addItemOrDrop(player, new ItemStack(Items.ARROW, 64));

                    // 3 strength 2 potions
                    ItemStack strengthPotion = new ItemStack(Items.SPLASH_POTION);
                    PotionUtils.setPotion(strengthPotion, Potions.STRENGTH);

                    List<MobEffectInstance> customEffects = Lists.newArrayList();
                    customEffects.add(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 1)); // Strength II for 3 minutes

                    PotionUtils.setCustomEffects(strengthPotion, customEffects);
                    strengthPotion.setCount(3);
                    addItemOrDrop(player, strengthPotion);

                    // 1 shulker boxes (why not)
                    addItemOrDrop(player, new ItemStack(Items.PURPLE_SHULKER_BOX, 1));

                    // 10 totems of undying
                    for (int i = 0; i < 5; i++) {
                        addItemOrDrop(player, new ItemStack(ModItems.TOTEM_OF_FIRE.get()));
                    }
                    // 5 totems of undying
                    for (int i = 0; i < 5; i++) {
                        addItemOrDrop(player, new ItemStack(ModItems.TOTEM_OF_TECHNO.get()));
                    }

                    // Magnet staff
                    addItemOrDrop(player, new ItemStack(ModItems.TOTEM_OF_MAGNET.get(), 1));

                    // 4 stacks of ender pearls
                    for (int i = 0; i < 4; i++) {
                        addItemOrDrop(player, new ItemStack(Items.ENDER_PEARL, 16));
                    }

                    // 1 stack of god orbs
                    addItemOrDrop(player, new ItemStack(ModItems.GOD_ORB.get(), 16));

                    // 1 stack of obsidian
                    addItemOrDrop(player, new ItemStack(Items.OBSIDIAN, 64));

                    // 1 stack of end crystals
                    addItemOrDrop(player, new ItemStack(Items.END_CRYSTAL, 64));

                    // 2 stacks of bottle o' enchanting
                    addItemOrDrop(player, new ItemStack(ModItems.COMPACT_BOTTLE.get(), 64));
                    addItemOrDrop(player, new ItemStack(ModItems.COMPACT_BOTTLE.get(), 64));


                    return 1;
                }));
    }

    public static void addItemOrDrop(ServerPlayer player, ItemStack itemStack) {
        if (player.getInventory().add(itemStack)) {
            // Item successfully added to inventory
        } else {
            Level world = player.level();
            BlockPos pos = player.blockPosition();
            world.addFreshEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
        }
    }
}