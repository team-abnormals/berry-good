package com.teamabnormals.berry_good.core.mixin;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net/minecraft/world/level/block/CaveVinesBlock", "net/minecraft/world/level/block/CaveVinesPlantBlock"})
public class CaveVinesBlockMixin extends Block {
	private CaveVinesBlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "getCloneItemStack", at = @At("RETURN"), cancellable = true)
	private void getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
		if (BGConfig.COMMON.glowBerriesRequirePips.get()) {
			cir.setReturnValue(new ItemStack(BGItems.GLOW_BERRY_PIPS.get()));
		}
	}

	@Override
	public Item asItem() {
		return Items.GLOW_BERRIES;
	}

}