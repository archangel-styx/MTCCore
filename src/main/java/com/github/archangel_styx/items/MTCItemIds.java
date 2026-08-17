package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MTCItemIds {
    public static final ResourceKey<Item> SHROUD = create("shroud");
    public static final ResourceKey<Item> SUMMON_LIGHTNING = create("summon_lightning");
    public static final ResourceKey<Item> THE_HERMIT = create("the_hermit");
    public static final ResourceKey<Item> MYSTERY_CARD = create("mystery_card");
    public static final ResourceKey<Item> ROLLING_THUNDER = create("rolling_thunder");
    public static final ResourceKey<Item> SPITFIRE = create("spitfire");

    private static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, MTCCore.id(name));
    }
}
