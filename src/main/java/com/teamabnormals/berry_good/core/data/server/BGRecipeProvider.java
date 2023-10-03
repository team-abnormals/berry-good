package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.Blueprint;
import com.teamabnormals.blueprint.core.api.conditions.QuarkFlagRecipeCondition;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BGRecipeProvider extends RecipeProvider {
	public static final NotCondition NO_BERRY_SACK = new NotCondition(quarkFlag("berry_sack"));
	public static final NotCondition NO_GLOWBERRY_SACK = new NotCondition(quarkFlag("glowberry_sack"));

	public BGRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		conditionalNineBlockStorageRecipes(consumer, NO_BERRY_SACK, RecipeCategory.FOOD, Items.SWEET_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.SWEET_BERRY_BASKET.get());
		conditionalNineBlockStorageRecipes(consumer, NO_GLOWBERRY_SACK, RecipeCategory.FOOD, Items.GLOW_BERRIES, RecipeCategory.DECORATIONS, BGBlocks.GLOW_BERRY_BASKET.get());

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

	public static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage) {
		conditionalNineBlockStorageRecipes(consumer, condition, itemCategory, item, storageCategory, storage, BerryGood.MOD_ID + ":" + getSimpleRecipeName(storage), null, BerryGood.MOD_ID + ":" + getSimpleRecipeName(item), null);
	}

	protected static void conditionalNineBlockStorageRecipes(Consumer<FinishedRecipe> consumer, ICondition condition, RecipeCategory itemCategory, ItemLike item, RecipeCategory storageCategory, ItemLike storage, String storageLocation, @Nullable String itemGroup, String itemLocation, @Nullable String storageGroup) {
		conditionalRecipe(consumer, itemCategory, condition, ShapelessRecipeBuilder.shapeless(itemCategory, item, 9).requires(storage).group(storageGroup).unlockedBy(getHasName(storage), has(storage)), new ResourceLocation(itemLocation));
		conditionalRecipe(consumer, storageCategory, condition, ShapedRecipeBuilder.shaped(storageCategory, storage).define('#', item).pattern("###").pattern("###").pattern("###").group(itemGroup).unlockedBy(getHasName(item), has(item)), new ResourceLocation(storageLocation));
	}

	public static void conditionalRecipe(Consumer<FinishedRecipe> consumer, RecipeCategory recipeCategory, ICondition condition, RecipeBuilder recipe, ResourceLocation id) {
		ConditionalRecipe.builder().addCondition(condition).addRecipe(consumer1 -> recipe.save(consumer1, id)).generateAdvancement(new ResourceLocation(id.getNamespace(), "recipes/" + recipeCategory.getFolderName() + "/" + id.getPath())).build(consumer, id);
	}

	public static QuarkFlagRecipeCondition quarkFlag(String flag) {
		return new QuarkFlagRecipeCondition(new ResourceLocation(Blueprint.MOD_ID, "quark_flag"), flag);
	}

	private static ResourceLocation getModConversionRecipeName(ItemLike result, ItemLike input) {
		return new ResourceLocation(BerryGood.MOD_ID, getConversionRecipeName(result, input));
	}
}