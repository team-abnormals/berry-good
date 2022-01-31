package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifiers;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.IAdvancementModifier.Mode;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.IndexedRequirementsModifier;
import com.teamabnormals.blueprint.core.util.modification.ConfiguredModifier;
import com.teamabnormals.blueprint.core.util.modification.ModifierDataProvider;
import com.teamabnormals.blueprint.core.util.modification.TargetedModifier;
import com.teamabnormals.blueprint.core.util.modification.targeting.ConditionedModifierTargetSelector;
import com.teamabnormals.blueprint.core.util.modification.targeting.ModifierTargetSelectorRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.PlacedBlockTrigger.TriggerInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BGAdvancementModifiersProvider {
	public static ModifierDataProvider<Advancement.Builder, Void, DeserializationContext> createDataProvider(DataGenerator dataGenerator) {
		return AdvancementModifiers.createDataProvider(dataGenerator, "Advancement Modifiers", BerryGood.MOD_ID,
				createModifier("husbandry/balanced_diet", criteria(Mode.MODIFY, Arrays.asList(consumeItem(BGItems.SWEET_BERRY_MINCE.get()), consumeItem(BGItems.SWEET_BERRY_MEATBALLS.get())))),
				createModifier("husbandry/plant_seed", indexedRequirements(0, Collections.singletonList(Pair.of("sweet_berry_bush_pips", new Criterion(TriggerInstance.placedBlock(BGBlocks.SWEET_BERRY_BUSH_PIPS.get()))))))
		);
	}

	private static ModifierDataProvider.ProviderEntry<Advancement.Builder, Void, DeserializationContext> createModifier(List<ResourceLocation> targets, String name, List<ConfiguredModifier<Advancement.Builder, ?, Void, DeserializationContext, ?>> modifiers) {
		return new ModifierDataProvider.ProviderEntry<>(
				new TargetedModifier<>(new ConditionedModifierTargetSelector<>(ModifierTargetSelectorRegistry.NAMES.withConfiguration(targets)), modifiers),
				new ResourceLocation(BerryGood.MOD_ID, name)
		);
	}

	private static Pair<String, Criterion> consumeItem(ItemLike item) {
		return Pair.of(item.asItem().getRegistryName().getPath(), new Criterion(ConsumeItemTrigger.TriggerInstance.usedItem(item.asItem())));
	}

	private static ModifierDataProvider.ProviderEntry<Advancement.Builder, Void, DeserializationContext> createModifier(String target, ConfiguredModifier<Advancement.Builder, ?, Void, DeserializationContext, ?> modifier) {
		return createModifier(Collections.singletonList(new ResourceLocation(target)), target, Collections.singletonList(modifier));
	}

	private static Pair<Optional<Map<String, Criterion>>, Optional<String[]>> collectCriterions(List<Pair<String, Criterion>> criterions) {
		Optional<Map<String, Criterion>> criterionMap = Optional.of(Maps.newHashMap());
		ArrayList<String> requirements = Lists.newArrayList();

		criterions.forEach(pair -> {
			criterionMap.get().put(BerryGood.MOD_ID + ":" + pair.getFirst(), pair.getSecond());
			requirements.add(BerryGood.MOD_ID + ":" + pair.getFirst());
		});

		return Pair.of(criterionMap, Optional.of(requirements.toArray(String[]::new)));
	}


	private static ConfiguredModifier<Advancement.Builder, ?, Void, DeserializationContext, ?> criteria(Mode mode, List<Pair<String, Criterion>> criterions) {
		Pair<Optional<Map<String, Criterion>>, Optional<String[]>> requirements = collectCriterions(criterions);
		ArrayList<String[]> reqs = Lists.newArrayList();
		for (String string : requirements.getSecond().get()) {
			reqs.add(new String[]{string});
		}

		return new ConfiguredModifier<>(AdvancementModifiers.CRITERIA_MODIFIER, new CriteriaModifier.Config(mode, requirements.getFirst(), Optional.of(reqs.toArray(String[][]::new))));
	}

	private static ConfiguredModifier<Advancement.Builder, ?, Void, DeserializationContext, ?> indexedRequirements(int index, List<Pair<String, Criterion>> criterions) {
		Pair<Optional<Map<String, Criterion>>, Optional<String[]>> requirements = collectCriterions(criterions);
		return new ConfiguredModifier<>(AdvancementModifiers.INDEXED_REQUIREMENTS_MODIFIER, new IndexedRequirementsModifier.Config(Mode.MODIFY, index, requirements.getFirst(), requirements.getSecond()));
	}
}