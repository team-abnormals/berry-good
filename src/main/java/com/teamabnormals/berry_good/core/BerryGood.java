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
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.CompletableFuture;

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

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			BGItems.setupTabEditors();
		});

		context.registerConfig(ModConfig.Type.COMMON, BGConfig.COMMON_SPEC);
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(BGCompat::registerCompat);
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		boolean includeServer = event.includeServer();
		BGBlockTagsProvider blockTags = new BGBlockTagsProvider(output, lookupProvider, helper);
		generator.addProvider(includeServer, blockTags);
		generator.addProvider(includeServer, new BGItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), helper));
		generator.addProvider(includeServer, new BGRecipeProvider(output));
		generator.addProvider(includeServer, new BGLootTableProvider(output));
		generator.addProvider(includeServer, new BGLootModifierProvider(output, lookupProvider));
		generator.addProvider(includeServer, new BGAdvancementModifierProvider(output, lookupProvider));

		boolean includeClient = event.includeClient();
		generator.addProvider(includeClient, new BGBlockStateProvider(output, helper));
		generator.addProvider(includeClient, new BGItemModelProvider(output, helper));
		generator.addProvider(includeClient, new BGLanguageProvider(output));
		generator.addProvider(includeClient, new BGSoundDefinitionsProvider(output, helper));
	}
}