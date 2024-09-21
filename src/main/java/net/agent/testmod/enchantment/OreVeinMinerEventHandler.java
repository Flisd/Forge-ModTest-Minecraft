package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber
public class OreVeinMinerEventHandler {

    private static final int MAX_ORES = 50;

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack tool = player.getMainHandItem();

        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.ORE_VEIN_MINER.get(), tool) > 0) {
            BlockState state = event.getState();
            BlockPos pos = event.getPos();
            ServerLevel world = (ServerLevel) event.getLevel();

            if (state.is(Tags.Blocks.ORES)) {
                Set<BlockPos> visited = new HashSet<>();
                mineConnectedOres(world, pos, player, tool, visited);
            }
        }
    }

    private static void mineConnectedOres(ServerLevel world, BlockPos pos, Player player, ItemStack tool, Set<BlockPos> visited) {
        if (visited.size() >= MAX_ORES) return;

        BlockState state = world.getBlockState(pos);
        if (!state.is(Tags.Blocks.ORES) || visited.contains(pos)) return;

        visited.add(pos);
        Block.dropResources(state, world, pos, null, player, tool);
        world.destroyBlock(pos, false);

        for (BlockPos offset : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            if (!offset.equals(pos)) {
                mineConnectedOres(world, offset, player, tool, visited);
            }
        }
    }
}