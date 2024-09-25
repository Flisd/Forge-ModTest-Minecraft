package net.agent.testmod.datagen.loot;

import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_SAPPHIRE_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.dropSelf(ModBlocks.LAUNCH_BLOCK.get());
        this.dropSelf(ModBlocks.ENDER_BLOCK.get());
        this.dropSelf(ModBlocks.ORE_LUCK_BLOCK.get());
        this.dropSelf(ModBlocks.CAMO_BLOCK.get());
        this.dropSelf(ModBlocks.DIAMOND_TNT.get());
        this.dropSelf(ModBlocks.PROJECTILE_SHOOTER_BLOCK.get());
        this.dropSelf(ModBlocks.BEACON_POW.get());
        this.dropSelf(ModBlocks.ORE_GEN_BLOCK.get());
        this.dropSelf(ModBlocks.GREEN_LIGHT_BLOCK.get());
        this.dropSelf(ModBlocks.SAPPHIRE_TOOL_CRATE_BLOCK.get());
        this.dropSelf(ModBlocks.KIT_CRATE_BLOCK.get());
        this.dropSelf(ModBlocks.VORTEX_BLOCK.get());
        this.dropSelf(ModBlocks.PITFALL_BLOCK.get());
        this.dropSelf(ModBlocks.XP_BLOCK.get());
        this.dropSelf(ModBlocks.FREEZE_TRAP_BLOCK.get());
//        this.dropSelf(ModBlocks.DICE_BLOCK.get());


        this.add(ModBlocks.ORE_LUCK_BLOCK.get(), block -> LootTable.lootTable());
        this.add(ModBlocks.SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.NETHER_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NETHER_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));
        this.add(ModBlocks.END_SAPPHIRE_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.END_SAPPHIRE_ORE.get(), ModItems.RAW_SAPPHIRE.get()));

        this.dropSelf(ModBlocks.SAPPHIRE_STAIRS.get());
        this.dropSelf(ModBlocks.SAPPHIRE_BUTTON.get());
        this.dropSelf(ModBlocks.SAPPHIRE_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_TRAP_DOOR.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SAPPHIRE_WALL.get());

        this.add(ModBlocks.SAPPHIRE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SAPPHIRE_SLAB.get()));
        this.add(ModBlocks.SAPPHIRE_DOOR.get(),
                block -> createDoorTable(ModBlocks.SAPPHIRE_DOOR.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}