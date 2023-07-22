package com.teamabnormals.berry_good.core.data.server.tags;

import java.util.concurrent.CompletableFuture;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.other.tags.BGItemTags;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class BGItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {

	public BGItemTagsProvider(PackOutput generator, CompletableFuture<Provider> provider, ExistingFileHelper helper) {
		super(generator, ForgeRegistries.Keys.ITEMS, provider, lookup -> ForgeRegistries.ITEMS.getResourceKey(lookup).get(), BerryGood.MOD_ID, helper);
	}

	@Override
	public void addTags(Provider provider) {
		this.tag(ItemTags.MUSIC_DISCS).add(BGItems.MUSIC_DISC_FOX.get());
		this.tag(BGItemTags.SEEDS_SWEET_BERRY).add(BGItems.SWEET_BERRY_PIPS.get());
		this.tag(BGItemTags.SEEDS_GLOW_BERRY).add(BGItems.GLOW_BERRY_PIPS.get());
		this.tag(Tags.Items.SEEDS).addTag(BGItemTags.SEEDS_SWEET_BERRY).addTag(BGItemTags.SEEDS_GLOW_BERRY);
	}

}