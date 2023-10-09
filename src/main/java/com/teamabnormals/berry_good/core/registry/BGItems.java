package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.common.item.BlueprintRecordItem;
import com.teamabnormals.blueprint.core.util.item.CreativeModeTabContentsPopulator;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

import static com.teamabnormals.berry_good.core.registry.BGBlocks.GLOW_BERRY_BASKET;
import static com.teamabnormals.berry_good.core.registry.BGBlocks.SWEET_BERRY_BASKET;
import static net.minecraft.world.item.CreativeModeTabs.*;
import static net.minecraft.world.item.crafting.Ingredient.of;

@EventBusSubscriber(modid = BerryGood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BGItems {
	public static final ItemSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> SWEET_BERRY_MINCE = HELPER.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MINCE)));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS = HELPER.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().food(BGFoods.SWEET_BERRY_MEATBALLS)));
	public static final RegistryObject<Item> SWEET_BERRY_PIPS = HELPER.createItem("sweet_berry_pips", () -> new ItemNameBlockItem(Blocks.SWEET_BERRY_BUSH, new Item.Properties()));

	public static final RegistryObject<Item> GLOW_BERRY_PIPS = HELPER.createItem("glow_berry_pips", () -> new ItemNameBlockItem(Blocks.CAVE_VINES, new Item.Properties()));
	public static final RegistryObject<Item> GLOWGURT = HELPER.createItem("glowgurt", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).food(BGFoods.GLOWGURT)));

	public static final RegistryObject<Item> MUSIC_DISC_FOX = HELPER.createItem("music_disc_fox", () -> new BlueprintRecordItem(2, BGSounds.MUSIC_DISC_FOX, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 116));

	public static class BGFoods {
		public static final FoodProperties SWEET_BERRY_MINCE = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F).build();
		public static final FoodProperties SWEET_BERRY_MEATBALLS = new FoodProperties.Builder().nutrition(10).saturationMod(0.8F).build();
		public static final FoodProperties GLOWGURT = new FoodProperties.Builder().nutrition(10).saturationMod(0.6F).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 3000), 1.0F).build();
	}

	public static void setupTabEditors() {
		CreativeModeTabContentsPopulator.mod(BerryGood.MOD_ID)
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && BGConfig.COMMON.sweetBerriesRequirePips.get())
				.addItemsAfter(of(Items.BEETROOT_SEEDS), SWEET_BERRY_PIPS)
				.editor(event -> event.getEntries().remove(new ItemStack(Items.SWEET_BERRIES)))
				.predicate(event -> event.getTabKey() == NATURAL_BLOCKS && BGConfig.COMMON.glowBerriesRequirePips.get())
				.addItemsAfter(of(Items.BEETROOT_SEEDS), GLOW_BERRY_PIPS)
				.editor(event -> event.getEntries().remove(new ItemStack(Items.GLOW_BERRIES)))
				.tab(NATURAL_BLOCKS)
				.addItemsAfter(of(Items.HAY_BLOCK), SWEET_BERRY_BASKET, GLOW_BERRY_BASKET)
				.tab(FOOD_AND_DRINKS)
				.addItemsAfter(of(Items.COOKED_PORKCHOP), SWEET_BERRY_MINCE, SWEET_BERRY_MEATBALLS)
				.addItemsBefore(of(Items.MILK_BUCKET), GLOWGURT)
				.tab(TOOLS_AND_UTILITIES)
				.addItemsAfter(of(Items.MUSIC_DISC_OTHERSIDE), MUSIC_DISC_FOX);
	}
}