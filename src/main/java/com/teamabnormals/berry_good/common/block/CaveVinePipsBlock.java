package com.teamabnormals.berry_good.common.block;

import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.ForgeHooks;

import java.util.Random;

public class CaveVinePipsBlock extends Block implements BonemealableBlock, CaveVines {
	private final VoxelShape SHAPE = Block.box(1.0D, 2.0D, 1.0D, 15.0D, 16.0D, 15.0D);
	private final Direction growthDirection = Direction.DOWN;

	public CaveVinePipsBlock(Block.Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(BERRIES, true));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return new ItemStack(BGItems.GLOW_BERRY_PIPS.get());
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
		super.tick(state, level, pos, random);
		if (!state.canSurvive(level, pos)) {
			level.destroyBlock(pos, true);
		}

		if (ForgeHooks.onCropsGrowPre(level, pos, state, random.nextDouble() < 0.1D)) {
			level.setBlock(pos, Blocks.CAVE_VINES.defaultBlockState(), 2);
			ForgeHooks.onCropsGrowPost(level, pos, state);
		}
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
		BlockPos blockpos = pos.relative(this.growthDirection.getOpposite());
		BlockState blockstate = level.getBlockState(blockpos);
		return blockstate.isFaceSturdy(level, blockpos, this.growthDirection) || blockstate.is(Blocks.CAVE_VINES);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context) {
		BlockState state = context.getLevel().getBlockState(context.getClickedPos().relative(this.growthDirection.getOpposite()));
		return !state.is(Blocks.CAVE_VINES) ? this.getStateForPlacement(context.getLevel()) : Blocks.CAVE_VINES.defaultBlockState();
	}

	public BlockState getStateForPlacement(LevelAccessor level) {
		return this.defaultBlockState();
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState p_53915_, LevelAccessor level, BlockPos pos, BlockPos p_53918_) {
		if (direction == this.growthDirection.getOpposite() && !state.canSurvive(level, pos)) {
			level.scheduleTick(pos, this, 1);
		}

		return super.updateShape(state, direction, p_53915_, level, pos, p_53918_);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
		super.createBlockStateDefinition(definition);
		definition.add(BERRIES);
	}

	@Override
	public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean isBonemealSuccess(Level level, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void performBonemeal(ServerLevel level, Random rand, BlockPos pos, BlockState state) {
		level.setBlock(pos, Blocks.CAVE_VINES.defaultBlockState(), 2);
	}
}