package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.berry_good.core.registry.BGJukeboxSongs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class BGLanguageProvider extends LanguageProvider {

	public BGLanguageProvider(PackOutput output) {
		super(output, BerryGood.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BGBlocks.SWEET_BERRY_BASKET.get(), "Basket of Sweet Berries");
		this.add(BGBlocks.GLOW_BERRY_BASKET.get(), "Basket of Glow Berries");
		this.add(
				BGItems.SWEET_BERRY_PIPS.get(), BGItems.SWEET_BERRY_MINCE.get(), BGItems.SWEET_BERRY_MEATBALLS.get(),
				BGItems.GLOW_BERRY_PIPS.get(), BGItems.GLOWGURT.get()
		);
		this.addDisc(BGItems.MUSIC_DISC_FOX.get(), BGJukeboxSongs.FOX, "RENREN - Fox");
	}

	private void add(Item... items) {
		for (Item item : items) {
			this.add(item, format(BuiltInRegistries.ITEM.getKey(item)));
		}
	}

	private void addDisc(Item item, ResourceKey<JukeboxSong> song, String description) {
		this.add(item, "Music Disc");
		ResourceLocation key = song.location();
		this.add("jukebox_song." + key.getNamespace() + "." + key.getPath(), description);
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}