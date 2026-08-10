package com.github.archangel_styx.spells.castbehaviors;

import com.github.archangel_styx.spells.SpellContext;
import net.minecraft.world.InteractionResult;

import java.util.function.Function;

public interface CastBehavior {
    public InteractionResult cast(SpellContext context, Function<SpellContext, InteractionResult> callback);
}
