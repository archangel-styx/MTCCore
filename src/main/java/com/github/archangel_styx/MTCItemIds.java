package com.github.archangel_styx;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MTCItemIds {
    public static final ResourceKey<Item> PLAYING_CARD = create("playing_card");
    public static final ResourceKey<Item> HEALTH_ITEM = create("health_item");
    public static final ResourceKey<Item> EXP_ITEM = create("exp_item");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MTCCore.MOD_ID, name));
    }
}
