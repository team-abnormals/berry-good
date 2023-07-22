package com.teamabnormals.berry_good.core.data.server.modifiers;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.common.advancement.modification.AdvancementModifierProvider;
import com.teamabnormals.blueprint.common.advancement.modification.modifiers.CriteriaModifier;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class BGAdvancementModifierProvider extends AdvancementModifierProvider {

	public BGAdvancementModifierProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(BerryGood.MOD_ID, generator, lookupProvider);
	}

	@Override
	protected void registerEntries(Provider provider) {
		CriteriaModifier.Builder balancedDiet = CriteriaModifier.builder(this.modId);
		Collection<RegistryObject<Item>> items = BGItems.HELPER.getDeferredRegister().getEntries();
		items.forEach(item -> {
			if (item.get().isEdible()) {
				balancedDiet.addCriterion(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), ConsumeItemTrigger.TriggerInstance.usedItem(item.get()));
			}
		});
		this.entry("husbandry/balanced_diet").selects("husbandry/balanced_diet").addModifier(balancedDiet.requirements(RequirementsStrategy.AND).build());
		this.entry("husbandry/plant_seed").selects("husbandry/plant_seed").addModifier(CriteriaModifier.builder(this.modId)
				.addCriterion("sweet_berry_bush", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(Blocks.SWEET_BERRY_BUSH))
				.addCriterion("cave_vines", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(Blocks.CAVE_VINES))
				.addIndexedRequirements(0, false, "sweet_berry_bush", "cave_vines").build());
	}
}