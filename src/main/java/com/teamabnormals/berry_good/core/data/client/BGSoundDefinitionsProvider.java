package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class BGSoundDefinitionsProvider extends SoundDefinitionsProvider {

	public BGSoundDefinitionsProvider(PackOutput output, ExistingFileHelper fileHelper) {
		super(output, BerryGood.MOD_ID, fileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(BGSoundEvents.MUSIC_DISC_FOX.get(), definition().with(sound(BerryGood.location("records/fox")).stream()));
	}
}