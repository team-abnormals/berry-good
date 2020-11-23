package com.minecraftabnormals.berry_good.core;

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
	public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

	public BerryGood() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		REGISTRY_HELPER.getDeferredBlockRegister().register(bus);
		REGISTRY_HELPER.getDeferredItemRegister().register(bus);

		MinecraftForge.EVENT_BUS.register(this);

		bus.addListener(this::commonSetup);
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			bus.addListener(this::clientSetup);
		});
	}

	private void commonSetup(FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			DataUtils.registerCompostable(BGRegistry.SWEET_BERRY_PIPS.get(), 0.30F);
		});
	}

	private void clientSetup(FMLClientSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			RenderTypeLookup.setRenderLayer(BGRegistry.SWEET_BERRY_BUSH_PIPS.get(), RenderType.getCutout());
		});
	}
}