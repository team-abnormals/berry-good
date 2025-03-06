package com.teamabnormals.berry_good.core.other;

import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.blueprint.core.util.DataUtil;

public class BGCompat {

	public static void registerCompat() {
		registerFlammables();
	}

	private static void registerFlammables() {
		DataUtil.registerFlammable(BGBlocks.SWEET_BERRY_BASKET.get(), 5, 20);
		DataUtil.registerFlammable(BGBlocks.GLOW_BERRY_BASKET.get(), 5, 20);
	}
}
