package net.agent.testmod.item.custom;

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

public class PremiumKitItem extends Item {
    public PremiumKitItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (!p_41432_.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) p_41433_;

            ItemStack chestplate = new ItemStack(Items.DIAMOND_CHESTPLATE);
            chestplate.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            chestplate.enchant(Enchantments.MENDING, 1);
            chestplate.enchant(Enchantments.UNBREAKING, 3);

            ItemStack helmet = new ItemStack(Items.IRON_HELMET);
            helmet.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            helmet.enchant(Enchantments.MENDING, 1);
            helmet.enchant(Enchantments.UNBREAKING, 3);

            ItemStack leggings = new ItemStack(Items.DIAMOND_LEGGINGS);
            leggings.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            leggings.enchant(Enchantments.MENDING, 1);
            leggings.enchant(Enchantments.UNBREAKING, 3);

            ItemStack boots = new ItemStack(Items.IRON_BOOTS);
            boots.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 4);
            boots.enchant(Enchantments.MENDING, 1);
            boots.enchant(Enchantments.UNBREAKING, 3);

            ItemStack sword = new ItemStack(Items.DIAMOND_SWORD);
            sword.enchant(Enchantments.SHARPNESS, 3);
            sword.enchant(Enchantments.KNOCKBACK, 1);
            sword.enchant(Enchantments.FIRE_ASPECT, 1);
            sword.enchant(Enchantments.UNBREAKING, 2);

            addItemOrDrop(serverPlayer, chestplate);
            addItemOrDrop(serverPlayer, helmet);
            addItemOrDrop(serverPlayer, leggings);
            addItemOrDrop(serverPlayer, boots);
            addItemOrDrop(serverPlayer, sword);
            addItemOrDrop(serverPlayer, new ItemStack(Items.DIAMOND_AXE));
            addItemOrDrop(serverPlayer, new ItemStack(Items.DIAMOND_PICKAXE));
            addItemOrDrop(serverPlayer, new ItemStack(Items.GOLDEN_APPLE, 16));
            addItemOrDrop(serverPlayer, new ItemStack(Items.GOLDEN_CARROT, 32));
            addItemOrDrop(serverPlayer, new ItemStack(Items.OAK_LOG, 16));

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
