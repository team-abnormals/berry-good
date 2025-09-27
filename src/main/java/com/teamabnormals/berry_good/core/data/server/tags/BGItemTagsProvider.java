package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGBlockTags;
import com.teamabnormals.berry_good.core.other.tags.BGItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.berry_good.core.registry.BGItems.*;

public class BGItemTagsProvider extends ItemTagsProvider {

	public BGItemTagsProvider(PackOutput output, CompletableFuture<Provider> provider, CompletableFuture<TagsProvider.TagLookup<Block>> lookup, ExistingFileHelper helper) {
		super(output, provider, lookup, BerryGood.MOD_ID, helper);
	}

	@Override
	protected void addTags(Provider provider) {
		this.tag(ItemTags.CHICKEN_FOOD).add(SWEET_BERRY_PIPS.get(), GLOW_BERRY_PIPS.get());
		this.tag(ItemTags.PARROT_FOOD).add(SWEET_BERRY_PIPS.get(), GLOW_BERRY_PIPS.get());
		this.tag(ItemTags.MEAT).add(SWEET_BERRY_MINCE.get(), SWEET_BERRY_PIPS.get());

		this.tag(Tags.Items.MUSIC_DISCS).add(MUSIC_DISC_FOX.get());

		this.tag(Tags.Items.FOODS).add(GLOWGURT.get());
		this.tag(Tags.Items.FOODS_RAW_MEAT).add(SWEET_BERRY_MINCE.get());
		this.tag(Tags.Items.FOODS_COOKED_MEAT).add(SWEET_BERRY_MEATBALLS.get());

		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(GLOW_BERRY_PIPS.get());

		this.copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
		this.copy(BGBlockTags.STORAGE_BLOCKS_SWEET_BERRY, BGItemTags.STORAGE_BLOCKS_SWEET_BERRY);
		this.copy(BGBlockTags.STORAGE_BLOCKS_GLOW_BERRY, BGItemTags.STORAGE_BLOCKS_GLOW_BERRY);
	}
}