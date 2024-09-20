package net.agent.testmod.event;

import net.agent.testmod.TestMod;
import net.agent.testmod.particle.ModParticles;
import net.agent.testmod.particle.custom.SapphireParticles;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventBusEvents {
    @SubscribeEvent
    public void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.SAPPHIRE_PARTICLES.get(), SapphireParticles.Provider::new);
    }
}
