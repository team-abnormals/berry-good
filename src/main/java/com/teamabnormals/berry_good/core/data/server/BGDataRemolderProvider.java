package com.teamabnormals.berry_good.core.data.server;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.remolder.data.RemolderProvider;
import com.teamabnormals.blueprint.common.remolder.util.LootRemolders;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.PackOutput.Target;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.concurrent.CompletableFuture;

public class BGDataRemolderProvider extends RemolderProvider {

	public BGDataRemolderProvider(PackOutput output, CompletableFuture<Provider> provider) {
		super(BerryGood.MOD_ID, Target.DATA_PACK, output, provider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("sweet_berry_bush").path("minecraft:loot_table/blocks/sweet_berry_bush").remolder(LootRemolders.addPool(LootPool.lootPool()
				.name(BerryGood.MOD_ID + ":sweet_berry_pips")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(BGItems.SWEET_BERRY_PIPS.get())).build()));

		this.entry("cave_vines").path("minecraft:loot_table/blocks/cave_vines").remolder(LootRemolders.addPool(LootPool.lootPool()
				.name(BerryGood.MOD_ID + ":glow_berry_pips")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(BGItems.GLOW_BERRY_PIPS.get())).build()));
	}
}