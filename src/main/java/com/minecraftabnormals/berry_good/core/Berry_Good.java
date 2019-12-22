package com.minecraftabnormals.berry_good.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.minecraftabnormals.berry_good.core.proxy.ClientProxy;
import com.minecraftabnormals.berry_good.core.proxy.ServerProxy;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import com.minecraftabnormals.berry_good.core.registry.other.BGCompostables;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("berry_good")
public class Berry_Good {

    public static final String MOD_ID = "berry_good";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Berry_Good() {
        DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onCommonSetup);
        BGBlocks.BLOCKS.register(modEventBus);
        BGItems.ITEMS.register(modEventBus);
    }

    private void onCommonSetup(FMLCommonSetupEvent event) {
		BGCompostables.registerCompostables();
    }
}