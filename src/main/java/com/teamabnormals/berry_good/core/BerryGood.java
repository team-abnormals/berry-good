package com.teamabnormals.berry_good.core;

import com.teamabnormals.berry_good.core.data.client.BGBlockStateProvider;
import com.teamabnormals.berry_good.core.data.client.BGItemModelProvider;
import com.teamabnormals.berry_good.core.data.client.BGLanguageProvider;
import com.teamabnormals.berry_good.core.data.client.BGSoundDefinitionsProvider;
import com.teamabnormals.berry_good.core.data.server.*;
import com.teamabnormals.berry_good.core.data.server.modifiers.BGAdvancementModifierProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGBlockTagsProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGItemTagsProvider;
import com.teamabnormals.berry_good.core.other.BGCompat;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@Mod(BerryGood.MOD_ID)
public class BerryGood {
	public static final String MOD_ID = "berry_good";
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

	public BerryGood(IEventBus bus, ModContainer container) {
		REGISTRY_HELPER.register(bus);

		bus.addListener(this::commonSetup);
		bus.addListener(this::dataSetup);

		if (FMLEnvironment.dist == Dist.CLIENT) {
			BGItems.setupTabEditors();
		}

		container.registerConfig(ModConfig.Type.COMMON, BGConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(BGCompat::registerCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> provider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean server = event.includeServer();
		BGDatapackProvider datapack = new BGDatapackProvider(output, provider);
		generator.addProvider(server, datapack);
		provider = datapack.getRegistryProvider();

		BGBlockTagsProvider blockTags = new BGBlockTagsProvider(output, provider, helper);
		generator.addProvider(server, blockTags);
		generator.addProvider(server, new BGItemTagsProvider(output, provider, blockTags.contentsGetter(), helper));
		generator.addProvider(server, new BGRecipeProvider(output, provider));
		generator.addProvider(server, new BGLootTableProvider(output, provider));
		generator.addProvider(server, new BGDataMapProvider(output, provider));
		generator.addProvider(server, new BGDataRemolderProvider(output, provider));
		generator.addProvider(server, new BGAdvancementModifierProvider(output, provider));

		boolean client = event.includeClient();
		generator.addProvider(client, new BGBlockStateProvider(output, helper));
		generator.addProvider(client, new BGItemModelProvider(output, helper));
		generator.addProvider(client, new BGLanguageProvider(output));
		generator.addProvider(client, new BGSoundDefinitionsProvider(output, helper));
	}
}