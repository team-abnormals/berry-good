package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class BGDataMapProvider extends DataMapProvider {

	public BGDataMapProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(output, provider);
	}

	@Override
	protected void gather(Provider provider) {
		this.builder(NeoForgeDataMaps.COMPOSTABLES)
				.add(BGBlocks.SWEET_BERRY_BASKET.get().asItem().builtInRegistryHolder(), new Compostable(1.0F), false)
				.add(BGBlocks.GLOW_BERRY_BASKET.get().asItem().builtInRegistryHolder(), new Compostable(1.0F), false)
				.add(BGItems.SWEET_BERRY_PIPS, new Compostable(0.3F), false)
				.add(BGItems.GLOW_BERRY_PIPS, new Compostable(0.3F), false);
	}
}