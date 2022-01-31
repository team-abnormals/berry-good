package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import org.apache.commons.lang3.text.WordUtils;

public class BGLanguageProvider extends LanguageProvider {

	public BGLanguageProvider(DataGenerator gen) {
		super(gen, BerryGood.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add(BGBlocks.SWEET_BERRY_BUSH_PIPS.get());
		this.add(BGBlocks.CAVE_VINE_PIPS.get());

		this.add(BGItems.SWEET_BERRY_PIPS.get());
		this.add(BGItems.SWEET_BERRY_MINCE.get());
		this.add(BGItems.SWEET_BERRY_MEATBALLS.get());
		this.add(BGItems.GLOW_BERRY_PIPS.get());
		this.add(BGItems.GLOWGURT.get());
		this.addDisc(BGItems.MUSIC_DISC_FOX.get(), "Mista Jub - Fox");
	}

	private void add(Item item) {
		if (item.getRegistryName() != null)
			this.add(item, format(item.getRegistryName()));
	}

	private void add(Block block) {
		if (block.getRegistryName() != null)
			this.add(block, format(block.getRegistryName()));
	}

	private void addDisc(Item item, String description) {
		if (item.getRegistryName() != null) {
			this.add(item, "Music Disc");
			this.add(item.getDescriptionId() + ".desc", description);
		}
	}

	private String format(ResourceLocation registryName) {
		return WordUtils.capitalizeFully(registryName.getPath().replace("_", " "));
	}
}