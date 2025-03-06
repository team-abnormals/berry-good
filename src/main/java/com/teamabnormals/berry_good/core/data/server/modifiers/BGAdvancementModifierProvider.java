package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import net.minecraft.advancements.AdvancementRequirements.Strategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class BGAdvancementModifierProvider extends AdvancementModifierProvider {
	private static final Item[] EDIBLE_ITEMS = new Item[]{BGItems.SWEET_BERRY_MINCE.get(), BGItems.SWEET_BERRY_MEATBALLS.get(), BGItems.GLOWGURT.get()};

	public BGAdvancementModifierProvider(PackOutput output, CompletableFuture<Provider> lookupProvider) {
		super(BerryGood.MOD_ID, output, lookupProvider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		for (Item item : EDIBLE_ITEMS) {
			balancedDiet.addCriterion(BuiltInRegistries.ITEM.getKey(item).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item));
		}
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(Strategy.AND).build());
		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("sweet_berry_bush", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(Blocks.SWEET_BERRY_BUSH))
				.addCriterion("cave_vines", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(Blocks.CAVE_VINES))
				.addIndexedRequirements(0, false, "sweet_berry_bush", "cave_vines").build());
	}
}