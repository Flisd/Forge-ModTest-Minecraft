package net.agent.testmod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        if(!useOnContext.getLevel().isClientSide) {
            BlockPos blockPos = useOnContext.getClickedPos();
            Player player = useOnContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= blockPos.getY() + 64; i++) {
                BlockState state = useOnContext.getLevel().getBlockState(blockPos.below(i));
                if (isValuableBlock(state)) {
                    outputValuableCords(blockPos.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("Womp womp nothing found"));
            }
        }

        useOnContext.getItemInHand().hurtAndBreak(1,useOnContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    private void outputValuableCords(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.COAL_ORE);
    }
}
