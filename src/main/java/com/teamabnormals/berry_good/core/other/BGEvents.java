package com.teamabnormals.berry_good.core.other;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGEvents {

	@SubscribeEvent
	public static void rightClickBlock(RightClickBlock event) {
		ItemStack stack = event.getItemStack();
		if ((BGConfig.COMMON.sweetBerriesRequirePips.get() && stack.is(Items.SWEET_BERRIES)) || (BGConfig.COMMON.glowBerriesRequirePips.get() && stack.is(Items.GLOW_BERRIES))) {
			event.setUseItem(Event.Result.DENY);
		}
	}
}