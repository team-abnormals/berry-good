package com.minecraftabnormals.berry_good.core.other;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;

public class BGItemTags {
	public static final ITag.INamedTag<Item> SEEDS_SWEET_BERRY = createForgeTag("seeds/sweet_berry");

	private static ITag.INamedTag<Item> createForgeTag(String name) {
		return ItemTags.bind("forge:" + name);
	}
}
