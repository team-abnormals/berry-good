package com.teamabnormals.berry_good.core;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.berry_good.core.data.client.BGItemModelProvider;
import com.teamabnormals.berry_good.core.data.client.BGLanguageProvider;
import com.teamabnormals.berry_good.core.data.client.BGSoundDefinitionsProvider;
import com.teamabnormals.berry_good.core.data.server.BGRecipeProvider;
import com.teamabnormals.berry_good.core.data.server.modifiers.BGAdvancementModifierProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGItemTagsProvider;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.Collections;
import java.util.function.Supplier;

@Mod(BerryGood.MOD_ID)
public class BerryGood {
	public static final String MOD_ID = "berry_good";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BerryGood() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext context = ModLoadingContext.get();
		MinecraftForge.EVENT_BUS.register(this);

		REGISTRY_HELPER.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		context.registerConfig(ModConfig.Type.COMMON, BGConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DataUtil.registerCompostable(BGItems.SWEET_BERRY_PIPS.get(), 0.30F);
			DataUtil.registerCompostable(BGItems.GLOW_BERRY_PIPS.get(), 0.30F);

			if (BGConfig.COMMON.glowBerriesGiveGlowing.get()) {
				Supplier<MobEffectInstance> instance = () -> new MobEffectInstance(MobEffects.GLOWING, 300);
				ObfuscationReflectionHelper.setPrivateValue(FoodProperties.class, Foods.GLOW_BERRIES, Collections.singletonList(Pair.of(instance, 1.0F)), "f_38728_");
			}
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		generator.addProvider(includeServer, new BGItemTagsProvider(generator, helper));
		generator.addProvider(includeServer, new BGRecipeProvider(generator));
		generator.addProvider(includeServer, new BGAdvancementModifierProvider(generator));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BGItemModelProvider(generator, helper));
		generator.addProvider(includeClient, new BGLanguageProvider(generator));
		generator.addProvider(includeClient, new BGSoundDefinitionsProvider(generator, helper));
	}
}