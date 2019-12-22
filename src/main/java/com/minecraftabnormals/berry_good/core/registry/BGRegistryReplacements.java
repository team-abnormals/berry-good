package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.berry_good.core.Berry_Good;

import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Berry_Good.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGRegistryReplacements {
	
	@ObjectHolder("minecraft:sweet_berries")
	public static Item SWEET_BERRIES;
	
	@SubscribeEvent
	public static void registerItemReplacements(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				
				new Item((new Item.Properties()).group(ItemGroup.FOOD).food(Foods.SWEET_BERRIES)).setRegistryName("minecraft:sweet_berries")
		);
	}
	
}