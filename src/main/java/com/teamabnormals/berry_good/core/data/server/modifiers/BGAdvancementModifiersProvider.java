package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.AdvancementModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.AdvancementModifier.Mode;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.IndexedRequirementsModifier;
import com.teamabnormals.blueprint.core.util.modification.AdvancementModifierProvider;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.PlacedBlockTrigger.TriggerInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BGAdvancementModifiersProvider extends AdvancementModifierProvider {

	public BGAdvancementModifiersProvider(DataGenerator dataGenerator) {
		super(dataGenerator, BerryGood.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		this.registerEntry("husbandry/balanced_diet", createCriteriaModifier(Mode.MODIFY, consumeItem(BGItems.SWEET_BERRY_MINCE.get()), consumeItem(BGItems.SWEET_BERRY_MEATBALLS.get())), new ResourceLocation("husbandry/balanced_diet"));
		this.registerEntry("husbandry/plant_seed", createIndexedRequirementsModifier(0, Collections.singletonList(Pair.of("sweet_berry_bush_pips", new Criterion(TriggerInstance.placedBlock(BGBlocks.SWEET_BERRY_BUSH_PIPS.get()))))), new ResourceLocation("husbandry/plant_seed"));
	}

	private static Pair<String, Criterion> consumeItem(ItemLike item) {
		return Pair.of(item.asItem().getRegistryName().getPath(), new Criterion(ConsumeItemTrigger.TriggerInstance.usedItem(item.asItem())));
	}

	private Pair<Optional<Map<String, Criterion>>, Optional<String[]>> collectCriterions(List<Pair<String, Criterion>> criterions) {
		Optional<Map<String, Criterion>> criterionMap = Optional.of(Maps.newHashMap());
		ArrayList<String> reqs = com.google.common.collect.Lists.newArrayList();

		criterions.forEach(pair -> {
			criterionMap.get().put(this.getModId() + ":" + pair.getFirst(), pair.getSecond());
			reqs.add(this.getModId() + ":" + pair.getFirst());
		});

		return Pair.of(criterionMap, Optional.of(reqs.toArray(String[]::new)));
	}

	private CriteriaModifier createCriteriaModifier(Mode mode, Pair<String, Criterion>... criterions) {
		Pair<Optional<Map<String, Criterion>>, Optional<String[]>> requirements = collectCriterions(Arrays.asList(criterions));
		ArrayList<String[]> reqs = Lists.newArrayList();
		for (String string : requirements.getSecond().get()) {
			reqs.add(new String[]{string});
		}

		return new CriteriaModifier(mode, requirements.getFirst(), Optional.of(reqs.toArray(String[][]::new)));
	}

	private IndexedRequirementsModifier createIndexedRequirementsModifier(int index, List<Pair<String, Criterion>> criterions) {
		Pair<Optional<Map<String, Criterion>>, Optional<String[]>> requirements = collectCriterions(criterions);
		return new IndexedRequirementsModifier(AdvancementModifier.Mode.MODIFY, index, requirements.getFirst(), requirements.getSecond());
	}
}