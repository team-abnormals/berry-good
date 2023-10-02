package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.loot.modification.LootModifierProvider;
import com.teamabnormals.blueprint.common.loot.modification.modifiers.LootPoolsModifier;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BGLootModifierProvider extends LootModifierProvider {

	public BGLootModifierProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
		super(BerryGood.MOD_ID, output, lookupProvider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		this.entry("sweet_berry_bush").selects(new ResourceLocation("blocks/sweet_berry_bush")).addModifier(new LootPoolsModifier(List.of(LootPool.lootPool()
				.name(BerryGood.MOD_ID + ":sweet_berry_pips")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(BGItems.SWEET_BERRY_PIPS.get())).build()), false));

		this.entry("cave_vines").selects(new ResourceLocation("blocks/cave_vines")).addModifier(new LootPoolsModifier(List.of(LootPool.lootPool()
				.name(BerryGood.MOD_ID + ":glow_berry_pips")
				.setRolls(ConstantValue.exactly(1.0F))
				.add(LootItem.lootTableItem(BGItems.GLOW_BERRY_PIPS.get())).build()), false));
	}
}