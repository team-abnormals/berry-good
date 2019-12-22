package com.minecraftabnormals.berry_good.core.registry.other;

import com.minecraftabnormals.berry_good.core.registry.BGItems;
import com.minecraftabnormals.berry_good.core.registry.BGRegistryReplacements;

import net.minecraft.block.ComposterBlock;

public class BGCompostables {

	public static void registerCompostables() {
		ComposterBlock.registerCompostable(0.3F, BGItems.SWEET_BERRY_PIPS.get());
		ComposterBlock.registerCompostable(0.3F, BGRegistryReplacements.SWEET_BERRIES);
	}
	
}
