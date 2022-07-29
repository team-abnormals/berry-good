package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.PlacedBlockTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;

public class BGAdvancementModifierProvider extends AdvancementModifierProvider {

	public BGAdvancementModifierProvider(DataGenerator generator) {
		super(generator, BerryGood.MOD_ID);
	}

	@Override
	protected void registerEntries() {
		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = BGItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());
		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("sweet_berry_bush_pips", PlacedBlockTrigger.TriggerInstance.placedBlock(BGBlocks.SWEET_BERRY_BUSH_PIPS.get()))
				.addCriterion("cave_vine_pips", PlacedBlockTrigger.TriggerInstance.placedBlock(BGBlocks.CAVE_VINE_PIPS.get()))
				.addIndexedRequirements(0, false, "sweet_berry_bush_pips", "cave_vine_pips").build());
	}
}