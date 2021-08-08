package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGSounds;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class SoundDefinitionGenerator extends SoundDefinitionsProvider {
	public SoundDefinitionGenerator(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(BGSounds.MUSIC_DISC_FOX.get(), definition().with(sound(new ResourceLocation(BerryGood.MOD_ID, "records/fox")).stream()));
	}
}