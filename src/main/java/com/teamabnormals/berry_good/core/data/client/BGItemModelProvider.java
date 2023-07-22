package com.teamabnormals.berry_good.core.data.client;

import com.teamabnormals.berry_good.core.BerryGood;
import com.teamabnormals.berry_good.core.registry.BGItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;


public class BGItemModelProvider extends ItemModelProvider {

	public BGItemModelProvider(PackOutput generator, ExistingFileHelper existingFileHelper) {
		super(generator, BerryGood.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		this.generated(BGItems.SWEET_BERRY_PIPS.get());
		this.generated(BGItems.SWEET_BERRY_MINCE.get());
		this.generated(BGItems.SWEET_BERRY_MEATBALLS.get());
		this.generated(BGItems.GLOW_BERRY_PIPS.get());
		this.generated(BGItems.GLOWGURT.get());
		this.generated(BGItems.MUSIC_DISC_FOX.get());
	}

	private void generated(Item item) {
		withExistingParent(name(item), "item/generated").texture("layer0", new ResourceLocation(this.modid, "item/" + name(item)));
	}

	private String name(ItemLike itemLike) {
		return ForgeRegistries.ITEMS.getKey(itemLike.asItem()).getPath();
	}
}