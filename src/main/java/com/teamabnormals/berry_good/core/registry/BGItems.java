package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.datapack.BGJukeboxSongs;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredHolder;

import static com.teamabnormals.berry_good.core.registry.BGBlocks.GLOW_BERRY_BASKET;
import static com.teamabnormals.berry_good.core.registry.BGBlocks.SWEET_BERRY_BASKET;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

public class BGItems {
	public static final ItemSubRegistryHelper ITEMS = BerryGood.REGISTRY_HELPER.getItemSubHelper();

	public static final DeferredHolder<Item, Item> SWEET_BERRY_MINCE = ITEMS.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MINCE)));
	public static final DeferredHolder<Item, Item> SWEET_BERRY_MEATBALLS = ITEMS.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MEATBALLS)));
	public static final DeferredHolder<Item, Item> SWEET_BERRY_PIPS = ITEMS.createItem("sweet_berry_pips", () -> new ItemNameBlockItem(Blocks.SWEET_BERRY_BUSH, new Item.Properties()));

	public static final DeferredHolder<Item, Item> GLOW_BERRY_PIPS = ITEMS.createItem("glow_berry_pips", () -> new ItemNameBlockItem(Blocks.CAVE_VINES, new Item.Properties()));
	public static final DeferredHolder<Item, Item> GLOWGURT = ITEMS.createItem("glowgurt", () -> new Item(new Item.Properties().stacksTo(1).food(BGFoods.GLOWGURT)));

	public static final DeferredHolder<Item, Item> MUSIC_DISC_FOX = ITEMS.createItem("music_disc_fox", () -> new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(BGJukeboxSongs.FOX)));

	public static class BGFoods {
		public static final FoodProperties SWEET_BERRY_MINCE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.3F).build();
		public static final FoodProperties SWEET_BERRY_MEATBALLS = new FoodProperties.Builder().nutrition(10).saturationModifier(0.8F).build();
		public static final FoodProperties GLOWGURT = new FoodProperties.Builder().nutrition(10).saturationModifier(0.6F).usingConvertsTo(Items.BOWL).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 3000), 1.0F).build();
	}

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(BerryGood.MOD_ID)
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && BGConfig.COMMON.sweetBerriesRequirePips.get())
				.addItemsAfter(of(Items.BEETROOT_SEEDS), SWEET_BERRY_PIPS)
				.editor(event -> event.remove(new ItemStack(Items.SWEET_BERRIES), TabVisibility.PARENT_AND_SEARCH_TABS));

		CreativeModeTabContentsPopulator.mod(BerryGood.MOD_ID)
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && BGConfig.COMMON.glowBerriesRequirePips.get())
				.addItemsAfter(of(Items.BEETROOT_SEEDS), GLOW_BERRY_PIPS)
				.editor(event -> event.remove(new ItemStack(Items.GLOW_BERRIES), TabVisibility.PARENT_AND_SEARCH_TABS));

		CreativeModeTabContentsPopulator.mod(BerryGood.MOD_ID)
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.HAY_BLOCK), SWEET_BERRY_BASKET, GLOW_BERRY_BASKET)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.COOKED_PORKCHOP), SWEET_BERRY_MINCE, SWEET_BERRY_MEATBALLS)
				.addItemsBefore(of(Items.MILK_BUCKET), GLOWGURT)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsAfter(of(Items.MUSIC_DISC_OTHERSIDE), MUSIC_DISC_FOX);
	}
}