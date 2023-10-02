package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGItemTags;
import com.teamabnormals.blueprint.core.other.tags.BlueprintItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.berry_good.core.registry.BGItems.*;

public class BGItemTagsProvider extends ItemTagsProvider {

	public BGItemTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> tagLookup, ExistingFileHelper fileHelper) {
		super(output, lookupProvider, tagLookup, BerryGood.MOD_ID, fileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(ItemTags.MUSIC_DISCS).add(MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(GLOW_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
		this.tag(BlueprintItemTags.CHICKEN_FOOD).add(SWEET_BERRY_PIPS.get(), GLOW_BERRY_PIPS.get());
	}
}