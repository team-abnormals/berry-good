package com.minecraftabnormals.berry_good.common.blocks;

import java.util.Random;

import com.minecraftabnormals.berry_good.core.registry.BGItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockSweetBerryBush extends BushBlock {

	public BlockSweetBerryBush(Block.Properties properties) {
	      super(properties);
	   }

	   public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		      return new ItemStack(BGItems.SWEET_BERRY_PIPS.get());
		   }
	   
	   public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
		      super.tick(state, worldIn, pos, random);
		      if (worldIn.getLightSubtracted(pos.up(), 0) >= 9 && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
		         worldIn.setBlockState(pos, Blocks.SWEET_BERRY_BUSH.getDefaultState(), 2);
		         net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		      }

		   }
	   
	   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		      return true;
		   }

		   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		      return true;
		   }
		   
		   public void grow(World worldIn, Random rand, BlockPos pos, BlockState state) {
			      worldIn.setBlockState(pos, Blocks.SWEET_BERRY_BUSH.getDefaultState(), 2);
			   }
	
}
