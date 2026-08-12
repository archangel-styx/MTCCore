package com.github.archangel_styx.spells.castbehaviors;

import com.github.archangel_styx.spells.costs.CostHandler;
import com.github.archangel_styx.spells.SpellContext;
import com.github.archangel_styx.spells.SpellEventScheduler;
import net.minecraft.world.InteractionResult;
import java.util.function.Function;

public class CantripBehavior implements CastBehavior {
    public InteractionResult cast(SpellContext context, Function<SpellContext, InteractionResult> callback) {
        if (!CostHandler.isPaid(context)) {
            return InteractionResult.FAIL;
        }

        SpellEventScheduler.schedule(callback, context, context.spell().getSpeed());

        return InteractionResult.SUCCESS_SERVER;
    }
}
