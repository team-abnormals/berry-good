package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.CookingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;


public class RecipeGenerator extends RecipeProvider {

	public RecipeGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapeless(BGItems.SWEET_BERRY_PIPS.get()).requires(Items.SWEET_BERRIES).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		ShapelessRecipeBuilder.shapeless(BGItems.SWEET_BERRY_MINCE.get()).requires(Items.SWEET_BERRIES).requires(Items.PORKCHOP).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(consumer);
		CookingRecipeBuilder.smelting(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, 200).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(consumer);
		cookingRecipesForMethod(consumer, "smoking", IRecipeSerializer.SMOKING_RECIPE, 100);
		cookingRecipesForMethod(consumer, "campfire_cooking", IRecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600);
	}

	private static void cookingRecipesForMethod(Consumer<IFinishedRecipe> recipeConsumer, String recipeConsumerIn, CookingRecipeSerializer<?> cookingMethod, int serializerIn) {
		CookingRecipeBuilder.cooking(Ingredient.of(BGItems.SWEET_BERRY_MINCE.get()), BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, serializerIn, cookingMethod).unlockedBy("has_sweet_berry_mince", has(BGItems.SWEET_BERRY_MINCE.get())).save(recipeConsumer, BerryGood.MOD_ID + ":sweet_berry_meatballs_from_" + recipeConsumerIn);
	}
}