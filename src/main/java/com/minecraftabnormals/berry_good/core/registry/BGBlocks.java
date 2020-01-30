package com.minecraftabnormals.berry_good.core.registry;

import com.minecraftabnormals.berry_good.common.blocks.BlockSweetBerryBush;
import com.minecraftabnormals.berry_good.core.Berry_Good;
import com.minecraftabnormals.berry_good.core.registry.util.RegistryUtils;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BGBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Berry_Good.MOD_ID);
    
    public static final RegistryObject<Block> SWEET_BERRY_BUSH_PIPS = RegistryUtils.createBlockNoItem("sweet_berry_bush_pips", () -> new BlockSweetBerryBush(Block.Properties.from(Blocks.SWEET_BERRY_BUSH)));

    public static void setupRenderLayer() {
    	RenderTypeLookup.setRenderLayer(BGBlocks.SWEET_BERRY_BUSH_PIPS.get(), RenderType.cutout());
    }
    
}
