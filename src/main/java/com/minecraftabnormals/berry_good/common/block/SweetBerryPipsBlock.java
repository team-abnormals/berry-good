package com.minecraftabnormals.berry_good.common.block;

import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class SweetBerryPipsBlock extends BushBlock implements IGrowable {
	private static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);

	public SweetBerryPipsBlock(Block.Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public ItemStack getCloneItemStack(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return new ItemStack(BGItems.SWEET_BERRY_PIPS.get());
	}

	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		super.tick(state, worldIn, pos, random);
		if (worldIn.getRawBrightness(pos.above(), 0) >= 9 && ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt(5) == 0)) {
			worldIn.setBlock(pos, Blocks.SWEET_BERRY_BUSH.defaultBlockState(), 2);
			net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
		}

	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (entityIn instanceof LivingEntity && entityIn.getType() != EntityType.FOX) {
			entityIn.makeStuckInBlock(state, new Vector3d(0.8F, 0.75D, 0.8F));
		}
	}

	@Override
	public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		worldIn.setBlock(pos, Blocks.SWEET_BERRY_BUSH.defaultBlockState(), 2);
	}
}