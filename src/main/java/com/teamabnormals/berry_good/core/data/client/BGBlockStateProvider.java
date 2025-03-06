package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import com.teamabnormals.blueprint.core.data.client.BlueprintBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BGBlockStateProvider extends BlueprintBlockStateProvider {

	public BGBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, BerryGood.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.directionalBlock(BGBlocks.SWEET_BERRY_BASKET);
		this.directionalBlock(BGBlocks.GLOW_BERRY_BASKET);
	}
}