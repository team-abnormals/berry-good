package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.berry_good.common.block.SweetBerryPipsBlock;
import com.minecraftabnormals.berry_good.core.BerryGood;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGRegistry {

	public static final RegistryHelper HELPER 	= BerryGood.REGISTRY_HELPER;

	public static final RegistryObject<Block> SWEET_BERRY_BUSH_PIPS = HELPER.createBlockNoItem("sweet_berry_bush_pips", () -> new SweetBerryPipsBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));

	public static final RegistryObject<Item> SWEET_BERRY_MINCE 		= HELPER.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(getMeatValue(false))));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS 	= HELPER.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(getMeatValue(true))));
	public static final RegistryObject<Item> SWEET_BERRY_PIPS 		= HELPER.createItem("sweet_berry_pips", () -> new BlockNamedItem(SWEET_BERRY_BUSH_PIPS.get(), new Item.Properties().group(ItemGroup.MISC)));

	public static final Food getMeatValue(boolean cooked) {
		return cooked ? new Food.Builder().hunger(10).saturation(0.7F).build() : new Food.Builder().hunger(5).saturation(0.2F).build();
	}
}
