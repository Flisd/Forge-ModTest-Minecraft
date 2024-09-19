package net.agent.testmod.block;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.custom.*;
import net.agent.testmod.block.custom.OreGenBlock;
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

    public static final RegistryObject<Block> ENDER_BLOCK = registerBlock("ender_block",
            () -> new EnderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> ORE_LUCK_BLOCK = registerBlock("ore_luck_block",
            () -> new RandomOreBlockGenBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> CAMO_BLOCK = registerBlock("camouflage_block",
            () -> new CamouflageBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> DIAMOND_TNT = registerBlock("diamond_tnt",
            () -> new DiamondTNTBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TNT)));

    public static final RegistryObject<Block> DICE_BLOCK = BLOCKS.register("dice_block",
            () -> new DiceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noLootTable()));

    public static final RegistryObject<Block> FOG_BLOCK = BLOCKS.register("fog_block",
            () -> new FogBlock(Block.Properties.ofFullCopy(Blocks.AIR).noLootTable()));

    public static final RegistryObject<Block> PROJECTILE_SHOOTER_BLOCK = BLOCKS.register("projectile_shooter_block",
            () -> new ProjectileShooter(Block.Properties.ofFullCopy(Blocks.DISPENSER)));

    public static final RegistryObject<Block> BEACON_POW = registerBlock("beacon_pow",
            () -> new BeaconSquareBlock(Block.Properties.ofFullCopy(Blocks.BEACON)));

    public static final RegistryObject<Block> ORE_GEN_BLOCK = registerBlock("ore_gen_block",
            () -> new OreGenBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> GREEN_LIGHT_BLOCK = registerBlock("green_light_block",
            () -> new GreenLightBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> SAPPHIRE_TOOL_CRATE_BLOCK= registerBlock("sapphire_tool_crate_block",
            () -> new SapphireToolCrateBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> KIT_CRATE_BLOCK= registerBlock("kit_crate_block",
            () -> new KitCrateBox(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> VORTEX_BLOCK = registerBlock("vortex_block",
            () -> new VortexBlock(Block.Properties.ofFullCopy(Blocks.IRON_BLOCK)));



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
