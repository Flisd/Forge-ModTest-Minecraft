package net.agent.testmod.block;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.custom.LaunchYou1000BlocksInTheAirBlock;
import net.agent.testmod.block.custom.SoundBlock;
import net.agent.testmod.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> RAW_SAPPHIRE_BLOCK = registerBlock("raw_sapphire_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_ORE = registerBlock("sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(6, 9), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .strength(2.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> NETHER_SAPPHIRE_ORE = registerBlock("nether_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 6), BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
                    .strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> END_SAPPHIRE_ORE = registerBlock("end_sapphire_ore",
            () -> new DropExperienceBlock(UniformInt.of(10, 20), BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)
                    .strength(3.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> LAUNCH_BLOCK = registerBlock("launch_block",
            () -> new LaunchYou1000BlocksInTheAirBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> SAPPHIRE_STAIRS = registerBlock("sapphire_stairs",
            () -> new StairBlock(ModBlocks.SAPPHIRE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_SLAB = registerBlock("sapphire_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_BUTTON = registerBlock("sapphire_button",
            () -> new ButtonBlock(BlockSetType.IRON, 10, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_FENCE = registerBlock("sapphire_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST),
                    SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK, WoodType.OAK));

    public static final RegistryObject<Block> SAPPHIRE_WALL = registerBlock("sapphire_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SAPPHIRE_DOOR = registerBlock("sapphire_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST).noOcclusion()));

    public static final RegistryObject<Block> SAPPHIRE_TRAP_DOOR = registerBlock("sapphire_trap_door",
            () -> new TrapDoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST).noOcclusion()));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> ret = BLOCKS.register(name, block);
        registerBlockItem(name, ret);
        return ret;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }


}
