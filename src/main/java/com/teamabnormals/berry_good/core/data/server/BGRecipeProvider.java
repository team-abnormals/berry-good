package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.data.server.BlueprintRecipeProvider;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class BGRecipeProvider extends BlueprintRecipeProvider {

	public BGRecipeProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(BerryGood.MOD_ID, output, provider);
	}

	@Override
	public void buildRecipes(RecipeOutput output) {
		storageRecipes(output, RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.SWEET_BERRY_BASKET.get());
		storageRecipes(output, RecipeCategory.FOOD, Items.GLOW_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.GLOW_BERRY_BASKET.get());

		conversionRecipe(output, BGItems.GLOW_BERRY_PIPS.get(), Items.GLOW_BERRIES, null);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.SWEET_BERRY_MINCE.get()).requires(Items.SWEET_BERRIES).requires(Items.PORKCHOP).unlockedBy("has_sweet_berries", has(Items.SWEET_BERRIES)).save(output);
		foodCookingRecipes(output, BGItems.SWEET_BERRY_MINCE.get(), BGItems.SWEET_BERRY_MEATBALLS.get());

		conversionRecipe(output, BGItems.SWEET_BERRY_PIPS.get(), Items.SWEET_BERRIES, null);
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, BGItems.GLOWGURT.get()).requires(Items.BOWL).requires(Items.GLOW_BERRIES, 2).requires(Items.SUGAR).requires(BlueprintItemTags.MILK).unlockedBy("has_glow_berries", has(Items.GLOW_BERRIES)).save(output);
	}
}