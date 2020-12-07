package com.minecraftabnormals.berry_good.core.other;

import com.minecraftabnormals.berry_good.core.BerryGood;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MOD_ID)
public class BGEvents {

	@SubscribeEvent
	public static void rightClickBlock(RightClickBlock event) {
		if (event.getItemStack().getItem() == Items.SWEET_BERRIES) {
			event.setUseItem(Event.Result.DENY);
		}
	}
}
