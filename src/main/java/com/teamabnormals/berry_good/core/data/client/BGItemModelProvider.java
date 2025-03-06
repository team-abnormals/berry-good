package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import com.teamabnormals.blueprint.core.data.client.BlueprintItemModelProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BGItemModelProvider extends BlueprintItemModelProvider {

	public BGItemModelProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, BerryGood.MOD_ID, helper);
	}

	@Override
	protected void registerModels() {
		this.generatedItem(
				BGItems.SWEET_BERRY_PIPS, BGItems.GLOW_BERRY_PIPS,
				BGItems.SWEET_BERRY_MINCE, BGItems.SWEET_BERRY_MEATBALLS,
				BGItems.GLOWGURT,
				BGItems.MUSIC_DISC_FOX
		);
	}
}