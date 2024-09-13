package net.agent.testmod.event;

import net.agent.testmod.sound.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber
public class RandomSoundEventHandler {
    private static final Random random = new Random();
    private static final int TICK_INTERVAL = 200; // Adjust this value for frequency of sound

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (event.phase == TickEvent.Phase.END && player.level().isClientSide) {
            if (random.nextInt(TICK_INTERVAL) == 0) {
                //player.level().playSound(player, player.blockPosition(), ModSounds.WIDE_PUTIN.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            }
        }
    }
}