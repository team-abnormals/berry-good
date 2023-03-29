package com.teamabnormals.berry_good.core;

import com.teamabnormals.berry_good.core.data.client.BGBlockStateProvider;
import com.teamabnormals.berry_good.core.data.client.BGItemModelProvider;
import com.teamabnormals.berry_good.core.data.client.BGLanguageProvider;
import com.teamabnormals.berry_good.core.data.client.BGSoundDefinitionsProvider;
import com.teamabnormals.berry_good.core.data.server.BGLootTableProvider;
import com.teamabnormals.berry_good.core.data.server.BGRecipeProvider;
import com.teamabnormals.berry_good.core.data.server.modifiers.BGAdvancementModifierProvider;
import com.teamabnormals.berry_good.core.data.server.modifiers.BGLootModifierProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGBlockTagsProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGItemTagsProvider;
import com.teamabnormals.berry_good.core.other.BGCompat;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
		event.enqueueWork(BGCompat::registerCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		BGBlockTagsProvider blockTags = new BGBlockTagsProvider(generator, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new BGItemTagsProvider(generator, blockTags, helper));
		generator.addProvider(includeServer, new BGRecipeProvider(generator));
		generator.addProvider(includeServer, new BGLootTableProvider(generator));
		generator.addProvider(includeServer, new BGLootModifierProvider(generator));
		generator.addProvider(includeServer, new BGAdvancementModifierProvider(generator));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BGBlockStateProvider(generator, helper));
		generator.addProvider(includeClient, new BGItemModelProvider(generator, helper));
		generator.addProvider(includeClient, new BGLanguageProvider(generator));
		generator.addProvider(includeClient, new BGSoundDefinitionsProvider(generator, helper));
	}
}