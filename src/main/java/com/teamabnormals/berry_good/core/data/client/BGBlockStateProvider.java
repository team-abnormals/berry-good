package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BGBlockStateProvider extends BlockStateProvider {

	public BGBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerStatesAndModels() {
		this.cross(BGBlocks.SWEET_BERRY_BUSH_PIPS.get());
		this.cross(BGBlocks.CAVE_VINE_PIPS.get());
	}

	private void cross(Block block) {
		String blockName = block.getRegistryName().getPath();
		this.simpleBlock(block, models().cross(blockName, new ResourceLocation(BerryGood.MOD_ID, "block/" + blockName)));
	}
}