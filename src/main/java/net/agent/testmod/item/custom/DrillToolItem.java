package net.agent.testmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


import java.util.List;

public class DrillToolItem extends DiggerItem {

    public DrillToolItem(float p_204108_, float p_204109_, Tier p_204110_, TagKey<Block> p_204111_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, p_204111_, p_204112_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
//        System.out.println(">>>>>> DRILL MINEBLOCK METHOD <<<<<");
        if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(1, miner, (p_220038_0_) -> {
                p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });

            // Check if the miner is a player
            if (miner instanceof Player) {
                Player player = (Player) miner;

                // Iterate over a 3x3 area around the original block
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            BlockPos currentPos = pos.offset(x, y, z);
                            BlockState currentState = world.getBlockState(currentPos);
                            // Check if the current block can be mined with a pickaxe
                            if (currentState.getDestroySpeed(world, currentPos) != 0.0F) {
                                // Mine the block
                                world.destroyBlock(currentPos, true, player);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.BREAKABLE || enchantment.category == EnchantmentCategory.DIGGER;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
        // Check if the entity is a player
        if (entity instanceof Player) {
            Player player = (Player) entity;
            // Check if the player is holding the drill
            if (player.getMainHandItem() == stack || player.getOffhandItem() == stack) {
                // Apply the effect to the player
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 100, 0, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 0, true, true));
            }
        }
    }

}