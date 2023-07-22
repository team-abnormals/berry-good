package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class BGSoundDefinitionsProvider extends SoundDefinitionsProvider {

	public BGSoundDefinitionsProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(BGSounds.MUSIC_DISC_FOX.get(), definition().with(sound(new ResourceLocation(BerryGood.MOD_ID, "records/fox")).stream()));
	}
}