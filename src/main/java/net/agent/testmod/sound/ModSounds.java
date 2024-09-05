package net.agent.testmod.sound;

import net.agent.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TestMod.MOD_ID);

    public static final RegistryObject<SoundEvent> TECHNO_NEVER_DIES = registerSoundEvents("techno_never_dies");

    public static final RegistryObject<SoundEvent> WIDE_PUTIN = registerSoundEvents("wide_putin");

    // .Ogg sound type in MONO nor stereo

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(TestMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
