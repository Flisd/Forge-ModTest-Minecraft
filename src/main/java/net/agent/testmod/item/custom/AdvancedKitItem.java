package net.agent.testmod.item.custom;

import net.agent.testmod.enchantment.ModEnchantments;
import net.agent.testmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class AdvancedKitItem extends Item {
    public AdvancedKitItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (!p_41432_.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) p_41433_;

            ItemStack helmet = new ItemStack(Items.NETHERITE_HELMET);
            helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            helmet.enchant(Enchantments.MENDING, 1);
            helmet.enchant(Enchantments.UNBREAKING, 3);
            helmet.enchant(ModEnchantments.REJUVENATION.get(), 1);

            ItemStack chestplate = new ItemStack(Items.NETHERITE_CHESTPLATE);
            chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            chestplate.enchant(Enchantments.MENDING, 1);
            chestplate.enchant(Enchantments.UNBREAKING, 3);
            chestplate.enchant(ModEnchantments.REJUVENATION.get(), 1);

            ItemStack leggings = new ItemStack(Items.NETHERITE_LEGGINGS);
            leggings.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            leggings.enchant(Enchantments.MENDING, 1);
            leggings.enchant(Enchantments.UNBREAKING, 3);
            leggings.enchant(ModEnchantments.REJUVENATION.get(), 1);

            ItemStack boots = new ItemStack(Items.NETHERITE_BOOTS);
            boots.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            boots.enchant(Enchantments.MENDING, 1);
            boots.enchant(Enchantments.UNBREAKING, 3);
            boots.enchant(ModEnchantments.SPEED_BOOTS.get(), 1);
            boots.enchant(ModEnchantments.REJUVENATION.get(), 1);

            ItemStack sword = new ItemStack(Items.NETHERITE_SWORD);
            sword.enchant(Enchantments.SHARPNESS, 5);
            sword.enchant(Enchantments.MENDING, 1);
            sword.enchant(Enchantments.KNOCKBACK, 1);
            sword.enchant(Enchantments.FIRE_ASPECT, 2);
            sword.enchant(Enchantments.UNBREAKING, 3);
            sword.enchant(ModEnchantments.REJUVENATION.get(), 1);

            ItemStack axe = new ItemStack(Items.NETHERITE_AXE);
            axe.enchant(Enchantments.SHARPNESS, 5);
            axe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            axe.enchant(Enchantments.MENDING, 1);
            axe.enchant(Enchantments.UNBREAKING, 3);
            axe.enchant(ModEnchantments.TREE_BYE.get(), 1);

            ItemStack pickaxe = new ItemStack(ModItems.SAPPHIRE_PICKAXE.get());
            pickaxe.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            pickaxe.enchant(Enchantments.MENDING, 1);
            pickaxe.enchant(Enchantments.UNBREAKING, 3);
            pickaxe.enchant(Enchantments.BLOCK_FORTUNE, 3);
            pickaxe.enchant(ModEnchantments.STRIP_MINING.get(), 1);

            ItemStack shovel = new ItemStack(Items.NETHERITE_SHOVEL);
            shovel.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
            shovel.enchant(Enchantments.MENDING, 1);
            shovel.enchant(Enchantments.UNBREAKING, 3);
            shovel.enchant(Enchantments.BLOCK_FORTUNE, 3);
            shovel.enchant(ModEnchantments.DOUBLE_TROUBLE.get(), 1);

            addItemOrDrop(serverPlayer, chestplate);
            addItemOrDrop(serverPlayer, helmet);
            addItemOrDrop(serverPlayer, leggings);
            addItemOrDrop(serverPlayer, boots);
            addItemOrDrop(serverPlayer, sword);
            addItemOrDrop(serverPlayer, axe);
            addItemOrDrop(serverPlayer, pickaxe);
            addItemOrDrop(serverPlayer, shovel);
            addItemOrDrop(serverPlayer, new ItemStack(Items.GOLDEN_APPLE, 48));
            addItemOrDrop(serverPlayer, new ItemStack(ModItems.STRAWBERRY.get(), 32));
            addItemOrDrop(serverPlayer, new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 2));
            addItemOrDrop(serverPlayer, new ItemStack(Items.ELYTRA, 1));
            addItemOrDrop(serverPlayer, new ItemStack(Items.FIREWORK_ROCKET, 64));

            // Reduce stack size by 1
            p_41433_.getItemInHand(p_41434_).shrink(1);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, p_41433_.getItemInHand(p_41434_));
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
