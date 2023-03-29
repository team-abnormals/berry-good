package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.common.block.BasketBlock;
import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BerryGood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BGBlocks {
	public static final BlockSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SWEET_BERRY_BASKET = HELPER.createBlock("sweet_berry_basket", () -> new BasketBlock(BGProperties.SWEET_BERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<Block> GLOW_BERRY_BASKET = HELPER.createBlock("glow_berry_basket", () -> new BasketBlock(BGProperties.GLOW_BERRY_BASKET), CreativeModeTab.TAB_DECORATIONS);

	public static class BGProperties {
		public static final Block.Properties SWEET_BERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_RED).strength(1.5F).sound(SoundType.WOOD);
		public static final Block.Properties GLOW_BERRY_BASKET = BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.GOLD).strength(1.5F).sound(SoundType.WOOD).lightLevel(state -> 14);
	}
}