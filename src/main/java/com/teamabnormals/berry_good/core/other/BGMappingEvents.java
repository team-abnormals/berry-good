package com.teamabnormals.berry_good.core.other;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.MissingMappingsEvent.Mapping;

import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGMappingEvents {

	@SubscribeEvent
	public static void onMissingMappings(MissingMappingsEvent event) {
		List<Mapping<Block>> blockMappings = event.getMappings(ForgeRegistries.Keys.BLOCKS, BerryGood.MOD_ID);

		Map<ResourceLocation, Block> blockRemapping = ImmutableMap.<ResourceLocation, Block>builder()
				.put(location("sweet_berry_bush_pips"), Blocks.SWEET_BERRY_BUSH)
				.put(location("cave_vine_pips"), Blocks.CAVE_VINES)
				.build();

		for (Mapping<Block> mapping : blockMappings) {
			Block block = blockRemapping.get(mapping.getKey());
			if (block != null && ForgeRegistries.BLOCKS.getKey(block) != null) {
				mapping.remap(block);
			}
		}
	}

	public static ResourceLocation location(String name) {
		return new ResourceLocation(BerryGood.MOD_ID, name);
	}
}