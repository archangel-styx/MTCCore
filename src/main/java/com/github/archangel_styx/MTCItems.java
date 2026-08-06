package com.github.archangel_styx;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class MTCItems {
    public static final Item PLAYING_CARD = register(MTCItemIds.PLAYING_CARD, Item::new, new Item.Properties());
    public static final Item HEALTH_ITEM = register(MTCItemIds.HEALTH_ITEM, Item::new, new Item.Properties());
    public static final Item EXP_ITEM = register(MTCItemIds.EXP_ITEM, Item::new, new Item.Properties());

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        Item item = factory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
