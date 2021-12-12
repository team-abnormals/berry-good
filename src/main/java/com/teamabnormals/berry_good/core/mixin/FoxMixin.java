package com.teamabnormals.berry_good.core.mixin;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Fox.class)
public abstract class FoxMixin extends LivingEntity {

	protected FoxMixin(EntityType<? extends LivingEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Inject(method = "populateDefaultEquipmentSlots", at = @At("TAIL"))
	private void populateDefaultEquipmentSlots(DifficultyInstance difficulty, CallbackInfo info) {
		if (BGConfig.COMMON.foxMusicDisc.get() && this.random.nextFloat() < BGConfig.COMMON.foxMusicDiscChance.get()) {
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(BGItems.MUSIC_DISC_FOX.get()));
		}
	}
}
