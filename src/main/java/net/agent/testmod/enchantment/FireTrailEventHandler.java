package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class FireTrailEventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        // Check if the player is wearing boots with the FireTrail enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.FIRE_TRAIL.get(), boots) > 0) {
            if (!player.level().isClientSide) {
                ServerLevel level = (ServerLevel) player.level();
                BlockPos pos = player.blockPosition();

                // Set the block the player has walked over (y+1) to fire
                BlockState fireState = Blocks.FIRE.defaultBlockState();
                level.setBlockAndUpdate(pos.atY((int)player.getY()), fireState);

                // Give the player fire resistance
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false));
            }
        }
    }
}
