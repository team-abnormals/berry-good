package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.common.block.CaveVinePipsBlock;
import com.teamabnormals.berry_good.common.block.SweetBerryPipsBlock;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BGBlocks {
	public static final BlockSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SWEET_BERRY_BUSH_PIPS = HELPER.createBlockNoItem("sweet_berry_bush_pips", () -> new SweetBerryPipsBlock(Block.Properties.copy(Blocks.SWEET_BERRY_BUSH)));
	public static final RegistryObject<Block> CAVE_VINE_PIPS = HELPER.createBlockNoItem("cave_vine_pips", () -> new CaveVinePipsBlock(Block.Properties.copy(Blocks.CAVE_VINES)));
}
