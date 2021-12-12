package com.teamabnormals.berry_good.core;

import com.mojang.datafixers.util.Pair;
import com.teamabnormals.berry_good.core.data.client.*;
import com.teamabnormals.berry_good.core.data.server.BGLootTableProvider;
import com.teamabnormals.berry_good.core.data.server.BGRecipeProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGBlockTagsProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGItemTagsProvider;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.util.DataUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import java.util.Collections;
import java.util.function.Supplier;

@Mod(BerryGood.MOD_ID)
public class BerryGood {
	public static final String MOD_ID = "berry_good";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BerryGood() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.register(bus);
		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		bus.addListener(this::clientSetup);
		bus.addListener(this::dataSetup);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BGConfig.COMMON_SPEC);
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

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(BGBlocks.CAVE_VINE_PIPS.get(), RenderType.cutout());
		});
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			BGBlockTagsProvider blockTagGen = new BGBlockTagsProvider(dataGenerator, existingFileHelper);
			dataGenerator.addProvider(blockTagGen);
			dataGenerator.addProvider(new BGItemTagsProvider(dataGenerator, blockTagGen, existingFileHelper));
			dataGenerator.addProvider(new BGLootTableProvider(dataGenerator));
			dataGenerator.addProvider(new BGRecipeProvider(dataGenerator));
		}

		if (event.includeClient()) {
			dataGenerator.addProvider(new BGItemModelProvider(dataGenerator, existingFileHelper));
			dataGenerator.addProvider(new BGBlockStateProvider(dataGenerator, existingFileHelper));
			dataGenerator.addProvider(new BGLanguageProvider(dataGenerator));
			dataGenerator.addProvider(new BGSoundDefinitionsProvider(dataGenerator, existingFileHelper));
		}
	}
}