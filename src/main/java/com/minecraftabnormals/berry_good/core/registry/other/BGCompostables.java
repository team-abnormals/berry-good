package com.minecraftabnormals.berry_good.core.registry.other;

import com.minecraftabnormals.berry_good.core.registry.BGItems;

import net.minecraft.block.ComposterBlock;

public class BGCompostables {

	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, BGItems.SWEET_BERRY_SEEDS.get());
	}
	
}
