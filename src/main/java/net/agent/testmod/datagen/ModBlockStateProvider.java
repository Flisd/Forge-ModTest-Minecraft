package net.agent.testmod.datagen;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.ENDER_BLOCK);

        blockWithItem(ModBlocks.FOG_BLOCK);
        blockWithItem(ModBlocks.PROJECTILE_SHOOTER_BLOCK);

        blockWithItem(ModBlocks.ORE_LUCK_BLOCK);

        blockWithItem(ModBlocks.CAMO_BLOCK);
        blockWithItem(ModBlocks.DIAMOND_TNT);
//        blockWithItem(ModBlocks.DICE_BLOCK);

        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);


        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.LAUNCH_BLOCK);
        simpleBlockWithItem(ModBlocks.BEACON_POW.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/beacon_pow")));

        stairsBlock(((StairBlock) ModBlocks.SAPPHIRE_STAIRS.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.SAPPHIRE_SLAB.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.SAPPHIRE_BUTTON.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SAPPHIRE_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.SAPPHIRE_FENCE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.SAPPHIRE_FENCE_GATE.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.SAPPHIRE_WALL.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.SAPPHIRE_DOOR.get()), modLoc("block/sapphire_door_bottom"), modLoc("block/sapphire_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.SAPPHIRE_TRAP_DOOR.get()), modLoc("block/sapphire_trapdoor"),true, "cutout");

        blockWithItem(ModBlocks.ORE_GEN_BLOCK);
        blockWithItem(ModBlocks.GREEN_LIGHT_BLOCK);
        blockWithItem(ModBlocks.SAPPHIRE_TOOL_CRATE_BLOCK);
        blockWithItem(ModBlocks.KIT_CRATE_BLOCK);
        blockWithItem(ModBlocks.VORTEX_BLOCK);
        blockWithItem(ModBlocks.PITFALL_BLOCK);
        blockWithItem(ModBlocks.XP_BLOCK);
        blockWithItem(ModBlocks.FREEZE_TRAP_BLOCK);
        blockWithItem(ModBlocks.HOLOGRAM_BLOCK);
        blockWithItem(ModBlocks.BLIND_TRAP_BLOCK);
        blockWithItem(ModBlocks.BAD_POT_BLOCK);
        blockWithItem(ModBlocks.GOOD_POT_BLOCK);
        blockWithItem(ModBlocks.LUCK_POT_BLOCK);
        blockWithItem(ModBlocks.DISPENSER_SQUARE);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}