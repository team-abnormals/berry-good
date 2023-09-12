package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGItemTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static com.teamabnormals.berry_good.core.registry.BGItems.*;

public class BGItemTagsProvider extends ItemTagsProvider {

	public BGItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, BerryGood.MOD_ID, helper);
	}

	@Override
	public void addTags() {
		this.tag(ItemTags.MUSIC_DISCS).add(MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(GLOW_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
		this.tag(BlueprintItemTags.CHICKEN_FOOD).add(SWEET_BERRY_PIPS.get(), GLOW_BERRY_PIPS.get());
	}
}