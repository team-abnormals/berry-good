package com.teamabnormals.berry_good.core.other;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class BGItemTags {
	public static final Tag.Named<Item> SEEDS_SWEET_BERRY = createForgeTag("seeds/sweet_berry");
	public static final Tag.Named<Item> SEEDS_GLOW_BERRY = createForgeTag("seeds/glow_berry");

	private static Tag.Named<Item> createForgeTag(String name) {
		return ItemTags.bind("forge:" + name);
	}
}
