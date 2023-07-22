package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;

public class BGLanguageProvider extends LanguageProvider {

	public BGLanguageProvider(PackOutput generator) {
		super(generator, BerryGood.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BGItems.SWEET_BERRY_PIPS.get());
		this.add(BGItems.SWEET_BERRY_MINCE.get());
		this.add(BGItems.SWEET_BERRY_MEATBALLS.get());
		this.add(BGItems.GLOW_BERRY_PIPS.get());
		this.add(BGItems.GLOWGURT.get());
		this.addDisc(BGItems.MUSIC_DISC_FOX.get(), "RENREN - Fox");
	}

	private void add(Item item) {
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		if (name != null)
			this.add(item, format(name));
	}

	private void addDisc(Item item, String description) {
		ResourceLocation name = ForgeRegistries.ITEMS.getKey(item);
		if (name != null) {
			this.add(item, "Music Disc");
			this.add(item.getDescriptionId() + ".desc", description);
		}
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}