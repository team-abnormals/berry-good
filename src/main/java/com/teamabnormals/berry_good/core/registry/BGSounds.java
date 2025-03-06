package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.core.events.LoadThisClassEvent;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;

@EventBusSubscriber(modid = BerryGood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BGSounds {
	@SubscribeEvent
	public static void $(LoadThisClassEvent event) {
	}

	public static final SoundSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getSoundSubHelper();

	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_FOX = HELPER.createSoundEvent("music_disc.fox");
}
