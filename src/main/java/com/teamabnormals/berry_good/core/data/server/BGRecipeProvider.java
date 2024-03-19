package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class BGRecipeProvider extends RecipeProvider {

	public BGRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		nineBlockStorageRecipes(consumer, RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.SWEET_BERRY_BASKET.get());
		nineBlockStorageRecipes(consumer, RecipeCategory.FOOD, Items.GLOW_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.GLOW_BERRY_BASKET.get());

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BGItems.SWEET_BERRY_PIPS.get()).requires(Items.SWEET_BERRIES).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.SWEET_BERRY_MINCE.get()).requires(Items.SWEET_BERRIES).requires(Items.PORKCHOP).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, 200).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(consumer);
		cookingRecipesForMethod(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
		cookingRecipesForMethod(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BGItems.GLOW_BERRY_PIPS.get()).requires(Items.GLOW_BERRIES).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.GLOWGURT.get()).requires(Items.BOWL).requires(Items.GLOW_BERRIES, 2).requires(Items.SUGAR).requires(BlueprintItemTags.MILK).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(consumer);
	}

	private static void cookingRecipesForMethod(Consumer<FinishedRecipe> recipeConsumer, String recipeConsumerIn, RecipeSerializer<? extends AbstractCookingRecipe> cookingMethod, int serializerIn) {
		SimpleCookingRecipeBuilder.generic(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, serializerIn, cookingMethod).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(recipeConsumer, BerryGood.MOD_ID + ":sweet_berry_meatballs_from_" + recipeConsumerIn);
	}

	protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		nineBlockStorageRecipes(consumer, category, item, storageCategory, storage, getSimpleModRecipeName(storage), null, getSimpleModRecipeName(item), null);
	}

	private static String getSimpleModRecipeName(ItemLike item) {
		return new ResourceLocation(BerryGood.MOD_ID, getSimpleRecipeName(item)).toString();
	}
}