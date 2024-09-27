package net.agent.testmod.block.entity;

import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.block.custom.DispenserSquareTileEntity;
import net.agent.testmod.block.custom.HologramBlock;
import net.agent.testmod.block.entity.renderer.OreGenBlockEntity;
import net.agent.testmod.block.custom.OreGenBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.agent.testmod.TestMod;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BeaconSquareBlockEntity>> BEACON_SQUARE = BLOCK_ENTITIES.register("beacon_square", () ->
            BlockEntityType.Builder.of(BeaconSquareBlockEntity::new, ModBlocks.BEACON_POW.get()).build(null));

    public static final RegistryObject<BlockEntityType<OreGenBlockEntity>> ORE_GEN_BLOCK_ENTITY = BLOCK_ENTITIES.register("ore_gen_block_entity", () ->
            BlockEntityType.Builder.of(OreGenBlockEntity::new, ModBlocks.ORE_GEN_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<HologramBlockEntity>> HOLOGRAM_BLOCK_ENTITY = BLOCK_ENTITIES.register("hologram_block_entity", () ->
            BlockEntityType.Builder.of(HologramBlockEntity::new, ModBlocks.HOLOGRAM_BLOCK.get()).build(null));

    public static final RegistryObject<BlockEntityType<DispenserSquareTileEntity>> DISPENSER_SQUARE = BLOCK_ENTITIES.register("dispenser_square", () ->
            BlockEntityType.Builder.of(DispenserSquareTileEntity::new, ModBlocks.HOLOGRAM_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
