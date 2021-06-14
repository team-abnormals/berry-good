package com.minecraftabnormals.berry_good.core;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.minecraftabnormals.berry_good.core.data.*;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
		event.enqueueWork(() -> DataUtil.registerCompostable(BGItems.SWEET_BERRY_PIPS.get(), 0.30F));
	}

	private void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> RenderTypeLookup.setRenderLayer(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), RenderType.cutout()));
	}

	private void dataSetup(GatherDataEvent event) {
		DataGenerator dataGenerator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer()) {
			BlockTagGenerator blockTagGen = new BlockTagGenerator(dataGenerator, existingFileHelper);
			dataGenerator.addProvider(blockTagGen);
			dataGenerator.addProvider(new ItemTagGenerator(dataGenerator, blockTagGen, existingFileHelper));
			dataGenerator.addProvider(new LootTableGenerator(dataGenerator));
			dataGenerator.addProvider(new RecipeGenerator(dataGenerator));
		}

		if (event.includeClient()) {
			dataGenerator.addProvider(new ItemModelGenerator(dataGenerator, existingFileHelper));
			dataGenerator.addProvider(new BlockModelGenerator(dataGenerator, existingFileHelper));
		}
	}
}