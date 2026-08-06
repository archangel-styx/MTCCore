package com.github.archangel_styx.spellcosts;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemCost implements SpellCostable<Map<Item, Integer>> {

    private Map<Item, Integer> values = new HashMap<>();

    public Map<Item, Integer> getCost() {
        return this.values;
    };

    public void addCost(Item item, int cost)
    {
        values.put(item, cost);
    }

    public void removeCost(Item item)
    {
        values.remove(item);
    }
}
