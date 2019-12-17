package com.minecraftabnormals.berry_good.core.registry.util;

import com.minecraftabnormals.berry_good.core.Berry_Good;
import com.minecraftabnormals.berry_good.core.registry.BGBlocks;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class RegistryUtils {

    public static BlockItem createWallOrFloorItem(Block floorBlock, Block wallBlock, ItemGroup itemGroup) {
        return (BlockItem) new WallOrFloorItem(floorBlock, wallBlock, new Item.Properties().group(itemGroup)).setRegistryName(floorBlock.getRegistryName());
    }

    public static BlockItem createTallItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new TallBlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }

    public static Item createSimpleItem(String name, ItemGroup itemGroup) {
        return new Item(new Item.Properties().group(itemGroup)).setRegistryName(Berry_Good.MOD_ID, name);
    }

    public static Item createSpawnEggForEntity(@SuppressWarnings("rawtypes") EntityType entityType, int eggColor1, int eggColor2, ItemGroup itemGroup) {
        return new SpawnEggItem(entityType, eggColor1, eggColor2, new Item.Properties().group(itemGroup)).setRegistryName(entityType.getRegistryName() + "_spawn_egg");
    }

    public static BlockItem createSimpleItemBlock(Block block, ItemGroup itemGroup) {
        return (BlockItem) new BlockItem(block, new Item.Properties().group(itemGroup)).setRegistryName(block.getRegistryName());
    }

    public static Item createItemBlockWithRarity(Block blockForInput, ItemGroup itemGroup, Rarity rarity) {
        return (BlockItem) new BlockItem(blockForInput, new Item.Properties().group(itemGroup).rarity(rarity)).setRegistryName(blockForInput.getRegistryName());
    }

    public static <I extends Item> RegistryObject<I> createItem(String name, Supplier<? extends I> supplier) {
        RegistryObject<I> item = BGItems.ITEMS.register(name, supplier);
        return item;
    }

    public static <B extends Block> RegistryObject<B> createBlock(String name, Supplier<? extends B> supplier, ItemGroup itemGroup) {
        RegistryObject<B> block = BGBlocks.BLOCKS.register(name, supplier);
        BGItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(itemGroup)));
        return block;
    }

    public static <B extends Block> RegistryObject<B> createBlockNoItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BGBlocks.BLOCKS.register(name, supplier);
        return block;
    }
}
