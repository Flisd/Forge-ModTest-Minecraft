package net.agent.testmod.villager;

import com.google.common.collect.ImmutableSet;
import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPE =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, TestMod.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,TestMod.MOD_ID);

    public static final RegistryObject<PoiType> ORE_LUCK_POI = POI_TYPE.register("ore_luck_poi",
            ()-> new PoiType(ImmutableSet.copyOf(ModBlocks.ORE_LUCK_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1,1));

    public static final RegistryObject<VillagerProfession> ORE_LUCK_MASTER =
            VILLAGER_PROFESSION.register("oreluckmaster", ()-> new VillagerProfession("oreluckmaster",
                    holder -> holder.get() == ORE_LUCK_POI.get(), holder -> holder.get() == ORE_LUCK_POI.get(),
                    ImmutableSet.of(),ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));

    public static void register(IEventBus eventBus){
        POI_TYPE.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
