package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.berry_good.core.registry.BGBlocks.GLOW_BERRY_BASKET;
import static com.teamabnormals.berry_good.core.registry.BGBlocks.SWEET_BERRY_BASKET;

public class BGBlockTagsProvider extends BlockTagsProvider {

	public BGBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(SWEET_BERRY_BASKET.get(), GLOW_BERRY_BASKET.get());
	}
}