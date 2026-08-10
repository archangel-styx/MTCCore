package com.github.archangel_styx.spells.costs;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemCost implements SpellCostable {

    private final Map<Item, Integer> values = new HashMap<>();

    public ItemCost(Map<Item, Integer> items)
    {
        values.putAll(items);
    }

    @Override
    public Map<Item, Integer> getCost() {
        return this.values;
    };
}
