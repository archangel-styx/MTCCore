package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import net.minecraft.resources.Identifier;

public class SpellKeys {
    public static final Identifier THE_HERMIT = create("the_hermit");
    public static final Identifier SHROUD_AURA = create("shroud_aura");
    public static final Identifier SUMMON_LIGHTNING_CANTRIP = create("summon_lightning_cantrip");
    public static final Identifier MYSTERY_CARD_CANTRIP = create("mystery_card_cantrip");

    private static Identifier create(String name) {
        return MTCCore.id(name);
    }
}
