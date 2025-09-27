package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

import static com.teamabnormals.berry_good.core.registry.BGBlocks.GLOW_BERRY_BASKET;
import static com.teamabnormals.berry_good.core.registry.BGBlocks.SWEET_BERRY_BASKET;

public class BGBlockTagsProvider extends BlockTagsProvider {

	public BGBlockTagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, ExistingFileHelper fileHelper) {
		super(output, lookupProvider, BerryGood.MOD_ID, fileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(BlockTags.MINEABLE_WITH_AXE).add(SWEET_BERRY_BASKET.get(), GLOW_BERRY_BASKET.get());
		this.tag(Tags.Blocks.STORAGE_BLOCKS).addTag(BGBlockTags.STORAGE_BLOCKS_SWEET_BERRY).addTag(BGBlockTags.STORAGE_BLOCKS_GLOW_BERRY);
		this.tag(BGBlockTags.STORAGE_BLOCKS_SWEET_BERRY).add(SWEET_BERRY_BASKET.get());
		this.tag(BGBlockTags.STORAGE_BLOCKS_GLOW_BERRY).add(GLOW_BERRY_BASKET.get());
	}
}