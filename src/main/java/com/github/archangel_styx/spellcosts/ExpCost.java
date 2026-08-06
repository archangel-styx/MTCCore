package com.github.archangel_styx.spellcosts;

public class ExpCost implements SpellCostable<Integer> {
    private Integer value;

    public Integer getCost() {
        return this.value;
    }

    public void setCost(Integer value) {
        this.value = value;
    }

    public void resetCost()
    {
        this.value = null;
    }
}
