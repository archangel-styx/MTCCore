package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class Spells {
    public static final Map<String, Spell> REGISTRY = new HashMap<>();

    public static final Spell LIGHTNING_CANTRIP = register(MTCCore.id("lightning_cantrip"), new LightningCantrip());
    public static final Spell MYSTERY_CARD = register(MTCCore.id("mystery_card"), new MysteryCard());

    public static void initialize() {
    }


    public static Spell register(Identifier id, Spell spell)
    {
        REGISTRY.put(id.toString(), spell);

        return spell;
    }
}
