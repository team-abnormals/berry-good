package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BGSoundEvents {
	public static final SoundSubRegistryHelper SOUND_EVENTS = BerryGood.REGISTRY_HELPER.getSoundSubHelper();

	public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_FOX = SOUND_EVENTS.createSoundEvent("music_disc.fox");
}
