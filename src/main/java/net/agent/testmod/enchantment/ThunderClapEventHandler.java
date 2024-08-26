package net.agent.testmod.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class ThunderClapEventHandler {

    private static final double JUMP_THRESHOLD = 0.3; // Adjusted to a reasonable jump threshold
    private static final int LIGHTNING_COUNT = 10;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        ItemStack boots = player.getItemBySlot(EquipmentSlot.FEET);

        // Check if the player is wearing boots with the Thunder Clap enchantment
        if (EnchantmentHelper.getItemEnchantmentLevel(ModEnchantments.THUNDER_CLAP.get(), boots) > 0) {
            if (!player.level().isClientSide) {
                ServerLevel level = (ServerLevel) player.level();
                BlockPos pos = player.blockPosition();
                Random rand = new Random();
                int randomIndex = rand.nextInt(100);
                // Check if the player has jumped over the threshold
                if (randomIndex < 6) {
                    if (player.getDeltaMovement().y > JUMP_THRESHOLD) {
                        // Spawn lightning around the player
                        for (int i = 0; i < LIGHTNING_COUNT; i++) {
                            BlockPos lightningPos = pos.offset(level.random.nextInt(10) - 5, 0, level.random.nextInt(10) - 5);
                            if (!lightningPos.equals(pos)) { // Ensure lightning doesn't spawn directly on the player
                                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                                lightningBolt.moveTo(lightningPos.getX(), lightningPos.getY(), lightningPos.getZ());
                                level.addFreshEntity(lightningBolt);
                            }
                        }
                    }
                }
            }
        }
    }
}
