package com.github.archangel_styx.spells;

import com.github.archangel_styx.spellcosts.SpellCostable;

import java.util.ArrayList;
import java.util.List;

public abstract class Spell {
    private String name;
    private List<SpellCostable> costs = new ArrayList<>();

    public List<SpellCostable> getCosts()
    {
        return this.costs;
    }
}