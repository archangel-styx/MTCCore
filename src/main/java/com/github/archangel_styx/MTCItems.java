package com.github.archangel_styx;

import com.github.archangel_styx.items.SpellCasterItem;
import com.github.archangel_styx.spells.TestLightningCantrip;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class MTCItems {
    public static final Item PLAYING_CARD = register(MTCItemIds.PLAYING_CARD, Item::new, new Item.Properties());
    public static final Item HEALTH_ITEM = register(MTCItemIds.HEALTH_ITEM, Item::new, new Item.Properties());
    public static final Item EXP_ITEM = register(MTCItemIds.EXP_ITEM, Item::new, new Item.Properties());
    public static final Item LIGHTNING_SPELL_ROD = register(MTCItemIds.LIGHTNING_SPELL_ROD,(p) -> {return new SpellCasterItem(p, new TestLightningCantrip()); }, new Item.Properties());

    public static void initialize()
    {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS)
                .register((tab) -> tab.accept(LIGHTNING_SPELL_ROD));
    }

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        Item item = factory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
