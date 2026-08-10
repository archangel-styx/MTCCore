package com.github.archangel_styx.spells.castbehaviors;

import com.github.archangel_styx.spells.costs.CostHandler;
import com.github.archangel_styx.spells.SpellContext;
import net.minecraft.world.InteractionResult;
import java.util.function.Function;

public class CantripBehavior implements CastBehavior {
    public InteractionResult cast(SpellContext context, Function<SpellContext, InteractionResult> callback) {
        if (!CostHandler.pay(context)) {
            return InteractionResult.FAIL;
        }
        callback.apply(context);
        return InteractionResult.SUCCESS;
    }
}
