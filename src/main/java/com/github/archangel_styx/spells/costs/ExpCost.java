package com.github.archangel_styx.spells.costs;

public class ExpCost implements SpellCostable {
    private final Integer value;

    public ExpCost(Integer amount)
    {
        this.value = amount;
    }

    @Override
    public Integer getCost() {
        return this.value;
    }
}
