package com.teamabnormals.berry_good.core.other;

import com.teamabnormals.berry_good.core.BGConfig;
import com.teamabnormals.berry_good.core.BerryGood;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGEvents {

	@SubscribeEvent
	public static void rightClickBlock(RightClickBlock event) {
		Item item = event.getItemStack().getItem();
		if ((BGConfig.COMMON.sweetBerriesRequirePips.get() && item == Items.SWEET_BERRIES) || (BGConfig.COMMON.glowBerriesRequirePips.get() && item == Items.GLOW_BERRIES)) {
			event.setUseItem(Event.Result.DENY);
		}
	}
}
