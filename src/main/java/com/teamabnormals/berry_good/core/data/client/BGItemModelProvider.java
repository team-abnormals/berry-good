package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;


public class BGItemModelProvider extends ItemModelProvider {

	public BGItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerModels() {
		registerGenerated(BGItems.SWEET_BERRY_PIPS.get());
		registerGenerated(BGItems.SWEET_BERRY_MINCE.get());
		registerGenerated(BGItems.SWEET_BERRY_MEATBALLS.get());
		registerGenerated(BGItems.GLOW_BERRY_PIPS.get());
		registerGenerated(BGItems.MUSIC_DISC_FOX.get());
	}

	private void registerGenerated(Item item) {
		ResourceLocation itemName = item.getRegistryName();
		withExistingParent(itemName.getPath(), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + itemName.getPath()));
	}
}