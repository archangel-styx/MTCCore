package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.spells.SpellKeys;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Function;

public class MTCItems {
    public static final ResourceKey<CreativeModeTab> SPELLS_TAB_KEY = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(), MTCCore.id("spells_tab")
    );

    public static final Item SHROUD = register(MTCItemIds.SHROUD, (props) -> {return new SpellCard(props, SpellKeys.SHROUD_AURA.toString()); }, new Item.Properties());
    public static final Item SUMMON_LIGHTNING = register(MTCItemIds.SUMMON_LIGHTNING, (props) -> { return new SpellCard(props, SpellKeys.SUMMON_LIGHTNING_CANTRIP.toString()); }, new Item.Properties());
    public static final Item THE_HERMIT = register(MTCItemIds.THE_HERMIT, (props) -> { return new SpellCard(props, SpellKeys.THE_HERMIT.toString()); }, new Item.Properties());
    public static final Item MYSTERY_CARD = register(MTCItemIds.MYSTERY_CARD, (props) -> { return new SpellCard(props, SpellKeys.MYSTERY_CARD_CANTRIP.toString()); }, new Item.Properties());


    public static void initialize()
    {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SPELLS_TAB_KEY, SPELLS_TAB);
    }

    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> factory, Item.Properties settings)
    {
        Item item = factory.apply(settings.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final CreativeModeTab SPELLS_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(MTCItems.MYSTERY_CARD))
            .title(Component.translatable("spellsTab.mtccore"))
            .displayItems((params, output) -> {
                output.accept(MYSTERY_CARD);
                output.accept(SHROUD);
                output.accept(SUMMON_LIGHTNING);
                output.accept(THE_HERMIT);
            })
            .build();
}
