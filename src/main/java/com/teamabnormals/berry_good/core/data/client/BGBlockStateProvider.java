package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BGBlockStateProvider extends BlockStateProvider {

	public BGBlockStateProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
		super(generator, BerryGood.MOD_ID, fileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.simpleCross(BGBlocks.SWEET_BERRY_BUSH_PIPS.get());
		this.simpleCross(BGBlocks.CAVE_VINE_PIPS.get());
	}

	private void simpleCross(Block block) {
		this.simpleBlock(block, models().cross(name(block), blockTexture(block)));
	}

	private String name(Block block) {
		return block.getRegistryName().getPath();
	}
}