package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.SoundSubRegistryHelper;
import com.minecraftabnormals.berry_good.core.BerryGood;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGSounds {
	public static final SoundSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getSoundSubHelper();

	public static final RegistryObject<SoundEvent> MUSIC_DISC_FOX = HELPER.createSoundEvent("music_disc.fox");
}
