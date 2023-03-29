package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.text.WordUtils;

public class BGLanguageProvider extends LanguageProvider {

	public BGLanguageProvider(DataGenerator generator) {
		super(generator, BerryGood.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BGBlocks.SWEET_BERRY_BASKET.get(), BGBlocks.GLOW_BERRY_BASKET.get());
		this.add(
				BGItems.SWEET_BERRY_PIPS.get(), BGItems.SWEET_BERRY_MINCE.get(), BGItems.SWEET_BERRY_MEATBALLS.get(),
				BGItems.GLOW_BERRY_PIPS.get(), BGItems.GLOWGURT.get()
		);
		this.addDisc(BGItems.MUSIC_DISC_FOX.get(), "RENREN - Fox");
	}

	private void add(Block... blocks) {
		for (Block block : blocks) {
			this.add(block, format(ForgeRegistries.BLOCKS.getKey(block)));
		}
	}

	private void add(Item... items) {
		for (Item item : items) {
			this.add(item, format(ForgeRegistries.ITEMS.getKey(item)));
		}
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