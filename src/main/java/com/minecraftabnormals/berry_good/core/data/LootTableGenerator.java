package com.minecraftabnormals.berry_good.core.data;

import com.google.common.collect.ImmutableList;
import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class LootTableGenerator extends ForgeLootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(Pair.of(BlockProvider::new, LootParameterSets.BLOCK));

	public LootTableGenerator(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return tables;
	}

	private static class BlockProvider extends BlockLootTables {
		@Override
		protected void addTables() {
			this.registerDropping(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), BGItems.SWEET_BERRY_PIPS.get());
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return ForgeRegistries.BLOCKS.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && BerryGood.MOD_ID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
		}
	}
}