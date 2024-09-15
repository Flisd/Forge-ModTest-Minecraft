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

public class TotemKitItem extends Item {
    public TotemKitItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (!p_41432_.isClientSide) {
            ServerPlayer serverPlayer = (ServerPlayer) p_41433_;

            for (int i = 0; i < 15; i++) {
                addItemOrDrop(serverPlayer, new ItemStack(ModItems.TOTEM_OF_FIRE.get()));
            }

            for (int i = 0; i < 5; i++) {
                addItemOrDrop(serverPlayer, new ItemStack(ModItems.TOTEM_OF_BEAST.get()));
            }

            for (int i = 0; i < 15; i++) {
                addItemOrDrop(serverPlayer, new ItemStack(Items.TOTEM_OF_UNDYING));
            }

            for (int i = 0; i < 15; i++) {
                addItemOrDrop(serverPlayer, new ItemStack(ModItems.TOTEM_OF_TECHNO.get()));
            }

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
