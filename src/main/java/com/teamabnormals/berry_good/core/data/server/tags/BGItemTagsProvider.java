package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGItemTags;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BGItemTagsProvider extends ItemTagsProvider {

	public BGItemTagsProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, new BlockTagsProvider(generator, BerryGood.MOD_ID, helper), BerryGood.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(ItemTags.MUSIC_DISCS).add(BGItems.MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(BGItems.SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(BGItems.GLOW_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
	}
}