package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.BGItemTags;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BGItemTagsProvider extends ItemTagsProvider {

	public BGItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
		super(generator, blockTagsProvider, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void addTags() {
		this.tag(ItemTags.MUSIC_DISCS).add(BGItems.MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(BGItems.SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(BGItems.GLOW_BERRY_PIPS.get());
		this.tag(BGItemTags.MILK).add(Items.MILK_BUCKET);
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
	}
}