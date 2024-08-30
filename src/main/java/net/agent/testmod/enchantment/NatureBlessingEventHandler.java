package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.level.block.BonemealableBlock;

@Mod.EventBusSubscriber
public class NatureBlessingEventHandler {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        ItemStack tool = player.getMainHandItem();
        BlockPos pos = event.getPos();
        if (event.getLevel() instanceof ServerLevel) {
            ServerLevel world = (ServerLevel) event.getLevel();
            BlockState state = world.getBlockState(pos);

            // Check if the tool has the Nature's Blessing enchantment and the block is a sapling or crop
            if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.NATURE.get(), tool) > 0 && (state.getBlock() instanceof BonemealableBlock)) {
                // Apply bonemeal effect without consuming the item
                if (applyBonemealWithoutConsuming(tool, world, pos, player)) {
                    event.setCanceled(true); // Cancel the event to prevent default behavior
                }
            }
        }
    }

    private static boolean applyBonemealWithoutConsuming(ItemStack stack, ServerLevel world, BlockPos pos, Player player) {
        BlockState blockstate = world.getBlockState(pos);
        if (blockstate.getBlock() instanceof BonemealableBlock bonemealableblock) {
            if (bonemealableblock.isValidBonemealTarget(world, pos, blockstate)) {
                if (bonemealableblock.isBonemealSuccess(world, world.random, pos, blockstate)) {
                    bonemealableblock.performBonemeal(world, world.random, pos, blockstate);
                }
                return true;
            }
        }
        return false;
    }
}
