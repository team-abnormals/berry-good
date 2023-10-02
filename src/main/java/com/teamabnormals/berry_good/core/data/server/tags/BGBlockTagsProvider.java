package com.teamabnormals.berry_good.core.data.server.tags;

import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
	}
}