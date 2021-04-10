package com.minecraftabnormals.berry_good.core.data;

import com.minecraftabnormals.berry_good.core.BerryGood;
import com.minecraftabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ItemModelGenerator extends ItemModelProvider {

	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		registerGenerated(BGItems.SWEET_BERRY_PIPS.get());
		registerGenerated(BGItems.SWEET_BERRY_MINCE.get());
		registerGenerated(BGItems.SWEET_BERRY_MEATBALLS.get());
		registerGenerated(BGItems.MUSIC_DISC_FOX.get());
	}

	private void registerGenerated(Item item) {
		ResourceLocation itemName = item.getRegistryName();
		withExistingParent(itemName.getPath(), "item/generated").texture("layer0", new ResourceLocation(BerryGood.MOD_ID, "item/" + itemName.getPath()));
	}
}