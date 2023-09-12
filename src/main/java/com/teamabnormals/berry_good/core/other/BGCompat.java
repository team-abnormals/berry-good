package com.teamabnormals.berry_good.core.other;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.util.DataUtil;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.Collections;
import java.util.function.Supplier;

public class BGCompat {

	public static void registerCompat() {
		registerAnimalFoods();
		registerCompostables();
		registerFlammables();
		addGlowBerryEffects();
	}

	private static void registerAnimalFoods() {
		DataUtil.addParrotFood(BGItems.SWEET_BERRY_PIPS.get(), BGItems.GLOW_BERRY_PIPS.get());
	}

	private static void registerCompostables() {
		DataUtil.registerCompostable(BGBlocks.SWEET_BERRY_BASKET.get(), 1.0F);
		DataUtil.registerCompostable(BGBlocks.GLOW_BERRY_BASKET.get(), 1.0F);

		DataUtil.registerCompostable(BGItems.SWEET_BERRY_PIPS.get(), 0.30F);
		DataUtil.registerCompostable(BGItems.GLOW_BERRY_PIPS.get(), 0.30F);
	}

	private static void registerFlammables() {
		DataUtil.registerFlammable(BGBlocks.SWEET_BERRY_BASKET.get(), 5, 20);
		DataUtil.registerFlammable(BGBlocks.GLOW_BERRY_BASKET.get(), 5, 20);
	}

	private static void addGlowBerryEffects() {
		if (BGConfig.COMMON.glowBerriesGiveGlowing.get()) {
			Supplier<MobEffectInstance> instance = () -> new MobEffectInstance(MobEffects.GLOWING, 300);
			ObfuscationReflectionHelper.setPrivateValue(FoodProperties.class, Foods.GLOW_BERRIES, Collections.singletonList(Pair.of(instance, 1.0F)), "f_38728_");
		}
	}
}
