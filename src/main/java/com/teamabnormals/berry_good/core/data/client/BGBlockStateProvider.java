package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile.ExistingModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BGBlockStateProvider extends BlockStateProvider {

	public BGBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
		super(output, BerryGood.MOD_ID, helper);
	}

	@Override
	protected void registerStatesAndModels() {
		this.directionalBlock(BGBlocks.SWEET_BERRY_BASKET.get());
		this.directionalBlock(BGBlocks.GLOW_BERRY_BASKET.get());
	}

	public void blockItem(Block block) {
		this.simpleBlockItem(block, new ExistingModelFile(blockTexture(block), this.models().existingFileHelper));
	}

	public void directionalBlock(Block block) {
		this.directionalBlock(block, models().cubeBottomTop(name(block), suffix(blockTexture(block), "_side"), suffix(blockTexture(block), "_bottom"), suffix(blockTexture(block), "_top")));
		this.blockItem(block);
	}

	private String name(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	private ResourceLocation suffix(ResourceLocation rl, String suffix) {
		return ResourceLocation.fromNamespaceAndPath(rl.getNamespace(), rl.getPath() + suffix);
	}
}