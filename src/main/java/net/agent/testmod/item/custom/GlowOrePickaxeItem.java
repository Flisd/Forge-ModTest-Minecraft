package net.agent.testmod.item.custom;

import net.agent.testmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class GlowOrePickaxeItem extends DiggerItem {

    public GlowOrePickaxeItem(float p_204108_, float p_204109_, Tier p_204110_, TagKey<Block> p_204111_, Properties p_204112_) {
        super(p_204108_, p_204109_, p_204110_, p_204111_, p_204112_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClientSide && state.getDestroySpeed(world, pos) != 0.0F) {
            stack.hurtAndBreak(1, miner, (p_220038_0_) -> {
                p_220038_0_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
        return true;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Player player = useOnContext.getPlayer();
        Level world = useOnContext.getLevel();
        ItemStack stack = useOnContext.getItemInHand();

        if (!world.isClientSide) {
            // Get all blocks in a certain radius around the player
            int radius = 30; // Adjust this value to change the search radius
            BlockPos playerPos = player.blockPosition();
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        BlockPos currentPos = playerPos.offset(x, y, z);
                        BlockState currentState = world.getBlockState(currentPos);
                        // Check if the current block is a valuable ore
                        if (isValuableOre(currentState)) {
                            // Replace the ore with a diamond block
                            world.setBlock(currentPos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
                        }
                    }
                }
            }

            // Reduce the item's durability
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(useOnContext.getHand()));
        }

        return InteractionResult.SUCCESS;
    }

    private boolean isValuableOre(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == EnchantmentCategory.BREAKABLE || enchantment.category == EnchantmentCategory.DIGGER;
    }
}
