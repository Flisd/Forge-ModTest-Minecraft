package net.agent.testmod.datagen;

import com.mojang.blaze3d.shaders.Effect;
import net.agent.testmod.TestMod;
import net.agent.testmod.block.ModBlocks;
import net.agent.testmod.item.ModItems;
import net.agent.testmod.item.custom.PosionSwordItem;
import net.agent.testmod.item.custom.PotionSwordItem;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE.get(),
            ModBlocks.SAPPHIRE_ORE.get(), ModBlocks.DEEPSLATE_SAPPHIRE_ORE.get(), ModBlocks.NETHER_SAPPHIRE_ORE.get(),
            ModBlocks.END_SAPPHIRE_ORE.get());


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    protected List<Potion> PotionsList = new ArrayList<>(ForgeRegistries.POTIONS.getValues());

    @Override
    protected void buildRecipes(RecipeOutput p_297267_) {
        oreSmelting(p_297267_, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(),2f, 200, "sapphire");
        oreBlasting(p_297267_, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE.get(),2f, 100, "sapphire");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK.get());

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

//        for (Potion potion : PotionsList) {
//            ItemStack potionStack = PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion);
//            PotionSwordItem potionSword = (PotionSwordItem) ModItems.POTION_SWORD.get();
//            MobEffectInstance strengthEffect = new MobEffectInstance(getEffectFromPotion(potionStack), 600, 1);
//            potionSword.addPotionEffect(strengthEffect);
//            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, potionSword, 1)
//                    .requires(ModItems.POTION_SWORD.get())
//                    .requires(Ingredient.of(potionStack.getItem()))
//                    .unlockedBy(getHasName(ModItems.POTION_SWORD.get()), has(Items.LINGERING_POTION))
//                    .save(p_297267_, "potion_sword_" + ForgeRegistries.POTIONS.getKey(potion));
//        }
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
//    public MobEffect getEffectFromPotion(ItemStack potionStack) {
//        if (potionStack.getItem() instanceof PotionItem) {
//            Potion potion = PotionUtils.getPotion(potionStack);
//            List<MobEffectInstance> effects = potion.getEffects();
//            if (!effects.isEmpty()) {
//                return effects.get(0).getEffect();
//            }
//        }
//        return null;
//    }
}