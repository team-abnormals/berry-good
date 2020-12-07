package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.berry_good.common.block.SweetBerryPipsBlock;
import com.minecraftabnormals.berry_good.core.BerryGood;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGBlocks {
	public static final BlockSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SWEET_BERRY_BUSH_PIPS = HELPER.createBlockNoItem("sweet_berry_bush_pips", () -> new SweetBerryPipsBlock(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));
}
