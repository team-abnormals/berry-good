package com.minecraftabnormals.berry_good.core.registry;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class BGFoods {

	public static final Food MEATBALLS(boolean cooked) {
		return cooked ? new Food.Builder().hunger(10).saturation(12.8F).build() : new Food.Builder().hunger(5).saturation(0.6F).build();
	}
}
