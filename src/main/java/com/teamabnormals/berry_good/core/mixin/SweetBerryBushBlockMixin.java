package com.teamabnormals.berry_good.core.mixin;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SweetBerryBushBlock.class)
public class SweetBerryBushBlockMixin extends Block {
	private SweetBerryBushBlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "getCloneItemStack", at = @At("RETURN"), cancellable = true)
	private void getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
		if (BGConfig.COMMON.sweetBerriesRequirePips.get()) {
			cir.setReturnValue(new ItemStack(BGItems.SWEET_BERRY_PIPS.get()));
		}
	}

	@Override
	public Item asItem() {
		return Items.SWEET_BERRIES;
	}

}