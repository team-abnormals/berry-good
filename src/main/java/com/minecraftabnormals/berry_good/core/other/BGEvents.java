package com.minecraftabnormals.berry_good.core.other;

import com.minecraftabnormals.berry_good.core.BerryGood;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BerryGood.MODID)
public class BGEvents {

    @SubscribeEvent
    public static void rightClickBlock(RightClickBlock event) {
        if (event.getItemStack().getItem() == Items.SWEET_BERRIES) {
            event.setUseItem(Event.Result.DENY);
        }
    }

}
