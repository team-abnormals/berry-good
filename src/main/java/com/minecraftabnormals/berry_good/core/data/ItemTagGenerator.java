package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.other.BGItemTags;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagGenerator extends ItemTagsProvider {

	public ItemTagGenerator(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
		super(generator, blockTagsProvider, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(ItemTags.MUSIC_DISCS).add(BGItems.MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(BGItems.SWEET_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY);
	}
}