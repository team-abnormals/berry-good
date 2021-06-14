package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagGenerator extends BlockTagsProvider {

	public BlockTagGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		this.tag(BlockTags.BEE_GROWABLES).add(BGBlocks.SWEET_BERRY_BUSH_PIPS.get());
	}
}