package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MTCItemIds {
    public static final ResourceKey<Item> LIGHTNING_CANTRIP = create("lightning_cantrip");
    public static final ResourceKey<Item> MYSTERY_CARD = create("mystery_card");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, MTCCore.id(name));
    }
}
