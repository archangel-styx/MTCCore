package com.github.archangel_styx.spells.costs;

public class HealthCost implements SpellCostable {
    private final Integer value;

    public HealthCost(Integer amount)
    {
        this.value = amount;
    }

    @Override
    public Integer getCost() {
        return this.value;
    }
}
