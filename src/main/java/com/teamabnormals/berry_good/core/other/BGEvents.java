package com.teamabnormals.berry_good.core.other;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;

@EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGEvents {

	@SubscribeEvent
	public static void rightClickBlock(RightClickBlock event) {
		ItemStack stack = event.getItemStack();
		if ((BGConfig.COMMON.sweetBerriesRequirePips.get() && stack.is(Items.SWEET_BERRIES)) || (BGConfig.COMMON.glowBerriesRequirePips.get() && stack.is(Items.GLOW_BERRIES))) {
			event.setUseItem(TriState.FALSE);
		}
	}

	@SubscribeEvent
	public static void onItemFinishUsing(LivingEntityUseItemEvent.Finish event) {
		LivingEntity entity = event.getEntity();
		if (!entity.level().isClientSide() && BGConfig.COMMON.glowBerriesGiveGlowing.get()) {
			entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
		}
	}
}