package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BlockModelGenerator extends BlockStateProvider {

	public BlockModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		registerCross(BGBlocks.SWEET_BERRY_BUSH_PIPS.get());
	}

	private void registerCross(Block block) {
		String blockName = block.getRegistryName().getPath();
		this.simpleBlock(block, models().cross(blockName, new ResourceLocation(BerryGood.MOD_ID, "block/" + blockName)));
	}
}