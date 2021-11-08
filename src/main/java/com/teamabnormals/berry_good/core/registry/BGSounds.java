package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.core.util.registry.SoundSubRegistryHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmllegacy.RegistryObject;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGSounds {
	public static final SoundSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> MUSIC_DISC_FOX = HELPER.createSoundEvent("music_disc.fox");
}
