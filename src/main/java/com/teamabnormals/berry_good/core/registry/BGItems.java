package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGItems {
	public static final ItemSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> SWEET_BERRY_MINCE = HELPER.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.SWEET_BERRY_MINCE)));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS = HELPER.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(Foods.SWEET_BERRY_MEATBALLS)));
	public static final RegistryObject<Item> SWEET_BERRY_PIPS = HELPER.createItem("sweet_berry_pips", () -> new ItemNameBlockItem(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> GLOW_BERRY_PIPS = HELPER.createItem("glow_berry_pips", () -> new ItemNameBlockItem(BGBlocks.CAVE_VINE_PIPS.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

	public static final RegistryObject<Item> MUSIC_DISC_FOX = HELPER.createItem("music_disc_fox", () -> new BlueprintRecordItem(2, BGSounds.MUSIC_DISC_FOX, new Item.Properties().stacksTo(1).tab(CreativeModeTab.TAB_MISC).rarity(Rarity.RARE)));

	public static class Foods {
		public static final FoodProperties SWEET_BERRY_MINCE = new FoodProperties.Builder().nutrition(5).saturationMod(0.2F).build();
		public static final FoodProperties SWEET_BERRY_MEATBALLS = new FoodProperties.Builder().nutrition(10).saturationMod(0.7F).build();
	}
}