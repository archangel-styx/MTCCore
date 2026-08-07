package com.github.archangel_styx.spells;

public class AuraSpell extends Spell {
    protected Integer duration;

    public AuraSpell() {
        super();
        this.spellType = SpellType.AURA;
    }
}
