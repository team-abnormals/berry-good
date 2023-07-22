package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import java.util.function.Consumer;

public class BGRecipeProvider extends RecipeProvider {

	public BGRecipeProvider(PackOutput generator) {
		super(generator);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.SWEET_BERRY_PIPS.get()).requires(Items.SWEET_BERRIES).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.SWEET_BERRY_MINCE.get()).requires(Items.SWEET_BERRIES).requires(Items.PORKCHOP).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, 200).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(consumer);
		cookingRecipesForMethod(consumer, "smoking", RecipeSerializer.SMOKING_RECIPE, 100);
		cookingRecipesForMethod(consumer, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.GLOW_BERRY_PIPS.get()).requires(Items.GLOW_BERRIES).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.GLOWGURT.get()).requires(Items.BOWL).requires(Items.GLOW_BERRIES, 2).requires(Items.SUGAR).requires(BlueprintItemTags.MILK).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(consumer);
	}

	private static void cookingRecipesForMethod(Consumer<FinishedRecipe> recipeConsumer, String recipeConsumerIn, RecipeSerializer<?> campfireCookingRecipe, int serializerIn) {
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), RecipeCategory.FOOD, BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, serializerIn).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(recipeConsumer, BerryGood.MOD_ID + ":sweet_berry_meatballs_from_" + recipeConsumerIn);
	}
}