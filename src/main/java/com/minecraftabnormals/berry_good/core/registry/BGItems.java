package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.minecraftabnormals.berry_good.core.BerryGood;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGItems {
	public static final ItemSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getItemSubHelper();

	public static final RegistryObject<Item> SWEET_BERRY_MINCE = HELPER.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.SWEET_BERRY_MINCE)));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS = HELPER.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(Foods.SWEET_BERRY_MEATBALLS)));
	public static final RegistryObject<Item> SWEET_BERRY_PIPS = HELPER.createItem("sweet_berry_pips", () -> new BlockNamedItem(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), new Item.Properties().group(ItemGroup.MISC)));

	static class Foods {
		public static final Food SWEET_BERRY_MINCE = new Food.Builder().hunger(5).saturation(0.2F).build();
		public static final Food SWEET_BERRY_MEATBALLS = new Food.Builder().hunger(10).saturation(0.7F).build();
	}
}