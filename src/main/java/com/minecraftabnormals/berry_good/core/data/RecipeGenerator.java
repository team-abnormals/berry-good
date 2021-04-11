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
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ShapelessRecipeBuilder.shapelessRecipe(BGItems.SWEET_BERRY_PIPS.get()).addIngredient(Items.SWEET_BERRIES).addCriterion("has_sweet_berries", hasItem(Items.SWEET_BERRIES)).build(consumer);
		ShapelessRecipeBuilder.shapelessRecipe(BGItems.SWEET_BERRY_MINCE.get()).addIngredient(Items.SWEET_BERRIES).addIngredient(Items.PORKCHOP).addCriterion("has_sweet_berries", hasItem(Items.SWEET_BERRIES)).build(consumer);
		CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(BGItems.SWEET_BERRY_MINCE.get()), BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, 200).addCriterion("has_sweet_berry_mince", hasItem(BGItems.SWEET_BERRY_MINCE.get())).build(consumer);
		cookingRecipesForMethod(consumer, "smoking", IRecipeSerializer.SMOKING, 100);
		cookingRecipesForMethod(consumer, "campfire_cooking", IRecipeSerializer.CAMPFIRE_COOKING, 600);
	}

	private static void cookingRecipesForMethod(Consumer<IFinishedRecipe> recipeConsumer, String recipeConsumerIn, CookingRecipeSerializer<?> cookingMethod, int serializerIn) {
		CookingRecipeBuilder.cookingRecipe(Ingredient.fromItems(BGItems.SWEET_BERRY_MINCE.get()), BGItems.SWEET_BERRY_MEATBALLS.get(), 0.35F, serializerIn, cookingMethod).addCriterion("has_sweet_berry_mince", hasItem(BGItems.SWEET_BERRY_MINCE.get())).build(recipeConsumer, BerryGood.MOD_ID + ":sweet_berry_meatballs_from_" + recipeConsumerIn);
	}
}