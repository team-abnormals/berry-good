package com.teamabnormals.berry_good.core.registry;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.blueprint.common.block.BlueprintDirectionalBlock;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(modid = BerryGood.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BGBlocks {
	public static final BlockSubRegistryHelper HELPER = BerryGood.REGISTRY_HELPER.getBlockSubHelper();

	public static final RegistryObject<Block> SWEET_BERRY_BASKET = HELPER.createBlock("sweet_berry_basket", () -> new BlueprintDirectionalBlock(BGProperties.SWEET_BERRY_BASKET));
	public static final RegistryObject<Block> GLOW_BERRY_BASKET = HELPER.createBlock("glow_berry_basket", () -> new BlueprintDirectionalBlock(BGProperties.GLOW_BERRY_BASKET));

	public static class BGProperties {
		public static final Block.Properties SWEET_BERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD).ignitedByLava();
		public static final Block.Properties GLOW_BERRY_BASKET = BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.BASS).strength(1.5F).sound(SoundType.WOOD).lightLevel(state -> 14).ignitedByLava();
	}
}