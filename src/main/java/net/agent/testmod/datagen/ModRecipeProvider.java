package net.agent.testmod.datagen;

import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.item.ModItems;
import net.agent.testmod.item.custom.PlaceStaff;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.NETHER_SAPPHIRE_ORE.get(),
            ModBlocks.END_SAPPHIRE_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(RecipeOutput p_297267_) {
        oreSmelting(p_297267_, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 2f, 200, "sapphire");
        oreBlasting(p_297267_, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 2f, 100, "sapphire");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_SAPPHIRE_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.RAW_SAPPHIRE.get())
                .unlockedBy(getHasName(ModItems.RAW_SAPPHIRE.get()), has(ModItems.RAW_SAPPHIRE.get()))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOUND_BLOCK.get())
                .pattern("D#D")
                .pattern("#S#")
                .pattern("D#D")
                .define('S', Blocks.SLIME_BLOCK)
                .define('D', Blocks.DIAMOND_BLOCK)
                .define('#', Blocks.OBSIDIAN)
                .unlockedBy(getHasName(ModItems.RAW_SAPPHIRE.get()), has(ModItems.RAW_SAPPHIRE.get()))
                .save(p_297267_);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SAPPHIRE.get(), 9)
                .requires(ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SAPPHIRE_BLOCK.get()), has(ModBlocks.SAPPHIRE_BLOCK.get()))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_DETECTOR.get())
                .pattern(" # ")
                .pattern("#S#")
                .pattern(" # ")
                .define('#', Blocks.IRON_BLOCK)
                .define('S', ModBlocks.SAPPHIRE_BLOCK.get())
                .unlockedBy(getHasName(ModItems.SAPPHIRE.get()), has(ModItems.SAPPHIRE.get()))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SPAWNER_DETECTOR.get())
                .pattern("`#`")
                .pattern("#S#")
                .pattern("`#`")
                .define('#', Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE)
                .define('S', Blocks.NETHERITE_BLOCK)
                .define('`', ModItems.METAL_DETECTOR.get())
                .unlockedBy(getHasName(ModItems.METAL_DETECTOR.get()), has(Blocks.NETHERITE_BLOCK))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.DIAMOND_TNT.get(),4)
                .pattern("ddd")
                .pattern("dtd")
                .pattern("ddd")
                .define('d', Items.DIAMOND)
                .define('t', Blocks.TNT)
                .unlockedBy(getHasName(Items.DIAMOND), has(Blocks.TNT))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENDER_BLOCK.get(),1)
                .pattern("ddd")
                .pattern("dtd")
                .pattern("ddd")
                .define('d', Items.ENDER_PEARL)
                .define('t', Blocks.DIAMOND_BLOCK)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Blocks.DIAMOND_BLOCK))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ORE_LUCK_BLOCK.get(),2)
                .pattern("123")
                .pattern("456")
                .pattern("789")
                .define('1', Blocks.DIAMOND_BLOCK)
                .define('2', Blocks.COAL_BLOCK)
                .define('3', Blocks.COPPER_BLOCK)
                .define('4', Blocks.GOLD_BLOCK)
                .define('5', Items.NETHERITE_INGOT)
                .define('6', Blocks.LAPIS_BLOCK)
                .define('7', Blocks.REDSTONE_BLOCK)
                .define('8', Blocks.EMERALD_BLOCK)
                .define('9', Blocks.IRON_BLOCK)
                .unlockedBy(getHasName(Blocks.DIAMOND_BLOCK), has(Blocks.COAL_BLOCK))
                .unlockedBy(getHasName(Blocks.COPPER_BLOCK), has(Blocks.GOLD_BLOCK))
                .unlockedBy(getHasName(Blocks.LAPIS_BLOCK), has(Blocks.REDSTONE_BLOCK))
                .unlockedBy(getHasName(Blocks.EMERALD_BLOCK), has(Blocks.IRON_BLOCK))
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRILL.get(),1)
                .pattern("ddd")
                .pattern("dsd")
                .pattern(" n ")
                .define('d', Items.DIAMOND_PICKAXE)
                .define('s', Items.NETHER_STAR)
                .define('n', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.DIAMOND_PICKAXE), has(Items.NETHER_STAR))
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TNT_SWORD.get(),1)
                .pattern("dsd")
                .pattern("dsd")
                .pattern(" x ")
                .define('s', Items.DIAMOND_SWORD)
                .define('d', ModBlocks.DIAMOND_TNT.get())
                .define('x', Items.STICK)
                .unlockedBy(getHasName(Items.DIAMOND_SWORD), has(ModBlocks.DIAMOND_TNT.get()))
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FOOD_ORB.get(),2)
                .pattern("gag")
                .pattern("fef")
                .pattern("gag")
                .define('g', Items.GOLDEN_CARROT)
                .define('a', Items.APPLE)
                .define('f', Items.ROTTEN_FLESH)
                .define('e', Items.ENDER_PEARL)
                .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.APPLE))
                .unlockedBy(getHasName(Items.ROTTEN_FLESH), has(Items.ENDER_PEARL))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOD_ORB.get(),1)
                .pattern("nbc")
                .pattern("beb")
                .pattern("cbd")
                .define('e', Items.ENDER_PEARL)
                .define('b', Items.BLAZE_ROD)
                .define('c', Items.COBWEB)
                .define('d', Items.DIAMOND_BLOCK)
                .define('n', Items.NETHERITE_INGOT)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.BLAZE_ROD))
                .unlockedBy(getHasName(Items.COBWEB), has(Items.DIAMOND_BLOCK))
                .unlockedBy(getHasName(Items.NETHERITE_INGOT), has(Items.NETHERITE_INGOT))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LIGHTNING_SWORD.get(),1)
                .pattern("fnf")
                .pattern("fnf")
                .pattern(" d ")
                .define('f', Items.FLINT_AND_STEEL)
                .define('d', Items.DIAMOND_SWORD)
                .define('n', Items.NETHERITE_SWORD)
                .unlockedBy(getHasName(Items.FLINT_AND_STEEL), has(Items.DIAMOND_SWORD))
                .unlockedBy(getHasName(Items.NETHERITE_SWORD), has(Items.NETHERITE_SWORD))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAGNET_STAFF.get(),1)
                .pattern("mtm")
                .pattern("mtm")
                .pattern("mtm")
                .define('t', Items.TOTEM_OF_UNDYING)
                .define('m', ModItems.TOTEM_OF_MAGNET.get())
                .unlockedBy(getHasName(Items.TOTEM_OF_UNDYING), has(Items.TOTEM_OF_UNDYING))
                .unlockedBy(getHasName(ModItems.TOTEM_OF_MAGNET.get()), has(ModItems.TOTEM_OF_MAGNET.get()))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PORTAL_ORB.get(),2)
                .pattern("dof")
                .pattern("oeo")
                .pattern("fod")
                .define('e', Items.ENDER_PEARL)
                .define('f', Items.FLINT_AND_STEEL)
                .define('d', Items.DIAMOND_BLOCK)
                .define('o', Items.OBSIDIAN)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.FLINT_AND_STEEL))
                .unlockedBy(getHasName(Items.DIAMOND_BLOCK), has(Items.OBSIDIAN))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.IVY_SWORD.get(),1)
                .pattern("fnf")
                .pattern("fnf")
                .pattern(" d ")
                .define('f', Items.FERMENTED_SPIDER_EYE)
                .define('d', Items.DIAMOND_SWORD)
                .define('n', Items.NETHERITE_SWORD)
                .unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(Items.DIAMOND_SWORD))
                .unlockedBy(getHasName(Items.NETHERITE_SWORD), has(Items.NETHERITE_SWORD))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BALL.get(),4)
                .pattern("eee")
                .pattern("eie")
                .pattern("eee")
                .define('e', Items.ENDER_PEARL)
                .define('i', Items.IRON_BLOCK)
                .unlockedBy(getHasName(Items.IRON_BLOCK), has(Items.IRON_BLOCK))
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TNT_ORB.get(),2)
                .pattern("ttt")
                .pattern("ted")
                .pattern("ddd")
                .define('e', Items.ENDER_PEARL)
                .define('t', Items.TNT)
                .define('d', ModBlocks.DIAMOND_TNT.get())
                .unlockedBy(getHasName(ModBlocks.DIAMOND_TNT.get()), has(ModBlocks.DIAMOND_TNT.get()))
                .unlockedBy(getHasName(Items.TNT), has(Items.ENDER_PEARL))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TOTEM_OF_MAGNET.get(),1)
                .pattern("iii")
                .pattern("iei")
                .pattern("iii")
                .define('e', Items.ENDER_PEARL)
                .define('i', Items.IRON_INGOT)
                .unlockedBy(getHasName(Items.ENDER_PEARL), has(Items.ENDER_PEARL))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(p_297267_);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COMPACT_BOTTLE.get(),1)
                .pattern("iii")
                .pattern("iei")
                .pattern("iii")
                .define('e', Items.DIAMOND)
                .define('i', Items.EXPERIENCE_BOTTLE)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(Items.EXPERIENCE_BOTTLE), has(Items.EXPERIENCE_BOTTLE))
                .save(p_297267_);
    }

    protected static void oreSmelting(RecipeOutput p_300202_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_300202_, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput p_298528_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_298528_, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    private static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput p_297621_, RecipeSerializer<T> p_251817_, AbstractCookingRecipe.Factory<T> p_312098_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for (ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_, p_312098_).group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_297621_, TestMod.MOD_ID + ":" + getItemName(p_250066_) + p_249236_ + "_" + getItemName(itemlike));
        }
    }
}
