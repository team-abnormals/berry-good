package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.common.item.BerryPipsBlockItem;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BerryGood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BGItems {
	public static final ItemSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> SWEET_BERRY_MINCE = HELPER.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MINCE)));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS = HELPER.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MEATBALLS)));
	public static final RegistryObject<Item> SWEET_BERRY_PIPS = HELPER.createItem("sweet_berry_pips", () -> new BerryPipsBlockItem(Blocks.SWEET_BERRY_BUSH, new Item.Properties()));

	public static final RegistryObject<Item> GLOW_BERRY_PIPS = HELPER.createItem("glow_berry_pips", () -> new BerryPipsBlockItem(Blocks.CAVE_VINES, new Item.Properties()));
	public static final RegistryObject<Item> GLOWGURT = HELPER.createItem("glowgurt", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).food(BGFoods.GLOWGURT)));

	public static final RegistryObject<Item> MUSIC_DISC_FOX = HELPER.createItem("music_disc_fox", () -> new BlueprintRecordItem(2, BGSounds.MUSIC_DISC_FOX, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 116));

	public static class BGFoods {
		public static final FoodProperties SWEET_BERRY_MINCE = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).build();
		public static final FoodProperties SWEET_BERRY_MEATBALLS = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F).build();
		public static final FoodProperties GLOWGURT = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 3000), 1.0F).build();
	}
	
	@SubscribeEvent
	public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
		MutableHashedLinkedMap<ItemStack, TabVisibility> map = event.getEntries();
		if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			map.putAfter(Items.SWEET_BERRIES.getDefaultInstance(), SWEET_BERRY_PIPS.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
			map.putAfter(Items.GLOW_BERRIES.getDefaultInstance(), GLOW_BERRY_PIPS.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
			map.remove(Items.SWEET_BERRIES.getDefaultInstance());
			map.remove(Items.GLOW_BERRIES.getDefaultInstance());
		}
		if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			map.putAfter(Items.SWEET_BERRIES.getDefaultInstance(), SWEET_BERRY_MINCE.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
			map.putAfter(SWEET_BERRY_MINCE.get().getDefaultInstance(), SWEET_BERRY_MEATBALLS.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
			map.putAfter(Items.GLOW_BERRIES.getDefaultInstance(), GLOWGURT.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
		}
		if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			map.putBefore(Items.MUSIC_DISC_5.getDefaultInstance(), MUSIC_DISC_FOX.get().getDefaultInstance(), TabVisibility.PARENT_AND_SEARCH_TABS);
		}
	}
}