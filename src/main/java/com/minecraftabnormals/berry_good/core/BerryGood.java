package com.minecraftabnormals.berry_good.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.minecraftabnormals.berry_good.core.registry.BGRegistry;
import com.teamabnormals.abnormals_core.core.utils.DataUtils;
import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BerryGood.MODID)
@SuppressWarnings("deprecation")
public class BerryGood {

	public static final String MODID = "berry_good";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);
	public static final RegistryHelper REGISTRY_REPLACER = new RegistryHelper("minecraft");

	public BerryGood() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.getDeferredBlockRegister().register(modEventBus);
		REGISTRY_HELPER.getDeferredItemRegister().register(modEventBus);
		REGISTRY_REPLACER.getDeferredItemRegister().register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);

		modEventBus.addListener(this::commonSetup);
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			modEventBus.addListener(this::clientSetup);
		});
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			DataUtils.registerCompostable(0.3F, BGRegistry.SWEET_BERRY_PIPS.get());
			DataUtils.registerCompostable(0.3F, BGRegistry.SWEET_BERRIES.get());
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			RenderTypeLookup.setRenderLayer(BGRegistry.SWEET_BERRY_BUSH_PIPS.get(), RenderType.cutout());
		});
	}
}