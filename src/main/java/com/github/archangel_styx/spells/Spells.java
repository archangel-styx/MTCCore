package com.github.archangel_styx.spells;

import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class Spells {
    public static final Map<String, Spell> REGISTRY = new HashMap<>();

    public static final Spell THE_HERMIT = register(SpellKeys.THE_HERMIT, new HermitCantrip());
    public static final Spell SHROUD_AURA = register(SpellKeys.SHROUD_AURA, new ShroudAura());
    public static final Spell SUMMON_LIGHTNING_CANTRIP = register(SpellKeys.SUMMON_LIGHTNING_CANTRIP, new SummonLightningCantrip());
    public static final Spell MYSTERY_CARD_CANTRIP = register(SpellKeys.MYSTERY_CARD_CANTRIP, new MysteryCardCantrip());

    public static void initialize()
    {
    }


    public static Spell register(Identifier id, Spell spell)
    {
        REGISTRY.put(id.toString(), spell);
        return spell;
    }
}
