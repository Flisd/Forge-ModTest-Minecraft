package net.agent.testmod.item.custom;

import net.agent.testmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpawnerDetectorItem extends Item {
    public SpawnerDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if(!useOnContext.getLevel().isClientSide) {
            BlockPos blockPos = useOnContext.getClickedPos();
            Player player = useOnContext.getPlayer();
            boolean foundBlock = false;

            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos searchPos = blockPos.offset(x, y, z);
                        BlockState state = useOnContext.getLevel().getBlockState(searchPos);
                        if (isSpawnerBlock(state)) {
                            outputValuableCords(searchPos, player, state.getBlock());
                            foundBlock = true;
                            break;
                        }
                    }
                    if (foundBlock) break;
                }
                if (foundBlock) break;
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("Womp womp nothing found"));
            }
        }

        useOnContext.getItemInHand().hurtAndBreak(1,useOnContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("tooltip.testmod.metal_detector.tooltip"));
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }

    private void outputValuableCords(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isSpawnerBlock(BlockState state) {
        return state.getBlock() == Blocks.SPAWNER;
    }
}