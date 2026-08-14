package com.github.archangel_styx.spells.castbehaviors;

import com.github.archangel_styx.spells.SpellContext;
import com.github.archangel_styx.spells.SpellEventScheduler;
import com.github.archangel_styx.spells.costs.CostHandler;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public class ChargeBehavior implements CastBehavior {
    public InteractionResult cast(SpellContext context, Function<SpellContext, InteractionResult> callback) {
        Player player = context.player();
       if (!CostHandler.canPay(context)) {
           return InteractionResult.FAIL;
       }

       if (!player.isUsingItem()) {
           player.startUsingItem(context.hand());
           return InteractionResult.CONSUME;
       }

       if(player.getTicksUsingItem() < context.spell().getSpeed())
       {
           return InteractionResult.FAIL;
       }

       if(!CostHandler.isPaid(context))
       {
           return InteractionResult.FAIL;
       }
       SpellEventScheduler.schedule(callback, context, context.spell().getSpeed());
       return InteractionResult.SUCCESS;
    }
}