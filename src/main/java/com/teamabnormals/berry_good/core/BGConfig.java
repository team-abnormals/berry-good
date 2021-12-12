package com.teamabnormals.berry_good.core;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

@EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGConfig {

	public static class Common {
		public final ConfigValue<Boolean> sweetBerriesRequirePips;
		public final ConfigValue<Boolean> glowBerriesRequirePips;
		public final ConfigValue<Boolean> glowBerriesGiveGlowing;

		public final ConfigValue<Boolean> foxMusicDisc;
		public final ConfigValue<Double> foxMusicDiscChance;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("tweaks");
			builder.push("sweet_berries");
			sweetBerriesRequirePips = builder.comment("If Sweet Berry Bushes require pips to place, to prevent accidental placement").define("Sweet Berries require pips", true);
			builder.pop();
			builder.push("glow_berries");
			glowBerriesRequirePips = builder.comment("If Cave Vines require pips to place, to prevent accidental placement").define("Glow Berries require pips", true);
			glowBerriesGiveGlowing = builder.comment("If Glow Berries give Glowing upon consumption").define("Glow Berries give Glowing", true);
			builder.pop();
			builder.pop();

			builder.push("items");
			builder.push("music_disc_fox");
			foxMusicDisc = builder.comment("If Foxes can spawn with a Fox music disc").define("Enable Fox music disc", true);
			foxMusicDiscChance = builder.comment("The chance that a Fox has to spawn with a Fox music disc").define("Fox music disc chance", 0.01D);
			builder.pop();
			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = commonSpecPair.getRight();
		COMMON = commonSpecPair.getLeft();
	}
}