package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.LightningCantrip;
import com.github.archangel_styx.spells.MysteryCard;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import java.util.List;
import java.util.function.Function;

public class MTCItems {
    public static final ResourceKey<CreativeModeTab> SPELLS_TAB_KEY = ResourceKey.create(
        BuiltInRegistries.CREATIVE_MODE_TAB.key(), MTCCore.id("spells_tab")
    );

    public static final Item LIGHTNING_CANTRIP = register(MTCItemIds.LIGHTNING_CANTRIP,
            SpellCard::new,
            new Item.Properties()
                    .durability(10)
                    .component(DataComponents.LORE, new ItemLore(
                            List.of(Component.literal(new LightningCantrip().getDescription()))
                    ))
                    .component(MTCComponents.ACTIVE_SPELL, MTCCore.id("lightning_cantrip").toString()));

    public static final Item MYSTERY_CARD = register(MTCItemIds.MYSTERY_CARD,
            SpellCard::new,
            new Item.Properties()
                    .stacksTo(1)
                    .component(DataComponents.LORE, new ItemLore(
                            List.of(Component.literal(new MysteryCard().getDescription()))
                    ))
                    .component(MTCComponents.ACTIVE_SPELL, MTCCore.id("mystery_spell").toString()));


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
                output.accept(LIGHTNING_CANTRIP);
                output.accept(MYSTERY_CARD);
            })
            .build();
}
