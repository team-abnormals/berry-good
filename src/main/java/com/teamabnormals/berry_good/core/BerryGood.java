package com.teamabnormals.berry_good.core;

import java.util.concurrent.CompletableFuture;

import com.teamabnormals.berry_good.core.data.client.BGItemModelProvider;
import com.teamabnormals.berry_good.core.data.client.BGLanguageProvider;
import com.teamabnormals.berry_good.core.data.client.BGSoundDefinitionsProvider;
import com.teamabnormals.berry_good.core.data.server.BGRecipeProvider;
import com.teamabnormals.berry_good.core.data.server.modifiers.BGAdvancementModifierProvider;
import com.teamabnormals.berry_good.core.data.server.tags.BGItemTagsProvider;
import com.teamabnormals.berry_good.core.other.BGCompat;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
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
        PackOutput output = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<Provider> lookup = event.getLookupProvider();

		boolean includeServer = event.includeServer();
		generator.addProvider(includeServer, new BGItemTagsProvider(output, lookup, helper));
		generator.addProvider(includeServer, new BGRecipeProvider(output));
		generator.addProvider(includeServer, new BGAdvancementModifierProvider(output, lookup));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BGItemModelProvider(output, helper));
		generator.addProvider(includeClient, new BGLanguageProvider(output));
		generator.addProvider(includeClient, new BGSoundDefinitionsProvider(output, helper));
	}
}