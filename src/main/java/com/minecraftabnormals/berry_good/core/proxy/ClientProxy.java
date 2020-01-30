package com.minecraftabnormals.berry_good.core.proxy;

import com.minecraftabnormals.berry_good.core.registry.BGBlocks;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy {
    public ClientProxy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onClientSetup);
    }

    private void onClientSetup(FMLClientSetupEvent event) {
    	BGBlocks.setupRenderLayer();
    }
}
