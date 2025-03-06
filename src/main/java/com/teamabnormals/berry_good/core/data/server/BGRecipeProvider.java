package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class BGRecipeProvider extends RecipeProvider {

	public BGRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		nineBlockStorageRecipes(output, RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.SWEET_BERRY_BASKET.get());
		nineBlockStorageRecipes(output, RecipeCategory.FOOD, Items.GLOW_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.GLOW_BERRY_BASKET.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BGItems.SWEET_BERRY_PIPS.get()).requires(Items.SWEET_BERRIES).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.SWEET_BERRY_MINCE.get()).requires(Items.SWEET_BERRIES).requires(Items.PORKCHOP).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(output);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, 200).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(output);
		cookingRecipesForMethod(output, "smoking", RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, 100);
		cookingRecipesForMethod(output, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 600);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BGItems.GLOW_BERRY_PIPS.get()).requires(Items.GLOW_BERRIES).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(output);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.GLOWGURT.get()).requires(Items.BOWL).requires(Items.GLOW_BERRIES, 2).requires(Items.SUGAR).requires(BlueprintItemTags.MILK).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(output);
	}

	private static <T extends AbstractCookingRecipe> void cookingRecipesForMethod(RecipeOutput output, String recipeConsumerIn, RecipeSerializer<T> cookingMethod, AbstractCookingRecipe.Factory<T> factory, int cookTime) {
		SimpleCookingRecipeBuilder.generic(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, cookTime, cookingMethod, factory).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(output, BerryGood.MOD_ID + ":sweet_berry_meatballs_from_" + recipeConsumerIn);
	}

	protected static void nineBlockStorageRecipes(RecipeOutput output, RecipeCategory category, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		nineBlockStorageRecipes(output, category, item, storageCategory, storage, getSimpleModRecipeName(storage), null, getSimpleModRecipeName(item), null);
	}

	private static String getSimpleModRecipeName(ItemLike item) {
		return ResourceLocation.fromNamespaceAndPath(BerryGood.MOD_ID, getSimpleRecipeName(item)).toString();
	}
}