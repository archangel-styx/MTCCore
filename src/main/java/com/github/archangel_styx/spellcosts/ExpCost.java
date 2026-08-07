package com.github.archangel_styx.spellcosts;

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
