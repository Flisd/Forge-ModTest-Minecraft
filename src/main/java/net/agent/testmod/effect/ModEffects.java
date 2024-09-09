package net.agent.testmod.effect;

import net.agent.testmod.TestMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TestMod.MOD_ID);

    RegistryObject<MobEffect> CURSE = EFFECTS.register("curse",
            ()-> new CurseEffect());

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
