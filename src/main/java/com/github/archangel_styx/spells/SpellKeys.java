package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.items.MTCItemIds;
import net.minecraft.resources.Identifier;

public class SpellKeys {
    public static final Identifier THE_HERMIT = create("the_hermit");
    public static final Identifier SHROUD_AURA = create("shroud_aura");
    public static final Identifier SUMMON_LIGHTNING = create("summon_lightning");
    public static final Identifier MYSTERY_CARD = create("mystery_card");
    public static final Identifier ROLLING_THUNDER = create("rolling_thunder");
    public static final Identifier SPITFIRE = create("spitfire");

    private static Identifier create(String name) {
        return MTCCore.id(name);
    }
}