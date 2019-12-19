package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.berry_good.core.Berry_Good;
import com.minecraftabnormals.berry_good.core.registry.util.RegistryUtils;

import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BGItems {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Berry_Good.MOD_ID);

    public static final RegistryObject<Item> SWEET_BERRY_MINCE = RegistryUtils.createItem("sweet_berry_mince", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(BGFoods.MEATBALLS(false))));
	public static final RegistryObject<Item> SWEET_BERRY_MEATBALLS = RegistryUtils.createItem("sweet_berry_meatballs", () -> new Item(new Item.Properties().group(ItemGroup.FOOD).food(BGFoods.MEATBALLS(true))));
	
    public static final RegistryObject<Item> SWEET_BERRY_PIPS = RegistryUtils.createItem("sweet_berry_pips", () -> new BlockNamedItem(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), new Item.Properties().group(ItemGroup.MISC)));
}
