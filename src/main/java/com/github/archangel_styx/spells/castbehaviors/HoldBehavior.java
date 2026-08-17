package com.github.archangel_styx.spells.castbehaviors;

import com.github.archangel_styx.spells.SpellContext;
import com.github.archangel_styx.spells.SpellEventScheduler;
import com.github.archangel_styx.spells.costs.CostHandler;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public class HoldBehavior implements CastBehavior {
    public InteractionResult cast(SpellContext context, Function<SpellContext, InteractionResult> callback)
    {
        if (!CostHandler.canPay(context))
        {
            return InteractionResult.FAIL;
        }

        Player player = context.player();

        if (!player.isUsingItem()) {
            player.startUsingItem(context.hand());
            return InteractionResult.CONSUME;
        }

        if (!CostHandler.isPaid(context))
            {
                if(context.level().isClientSide())
                {
                    player.stopUsingItem();
                    player.swing(player.getUsedItemHand(), true);
                }
                return InteractionResult.FAIL;
            }
        else {
            SpellEventScheduler.schedule(callback, context, 0);
            return InteractionResult.SUCCESS;
        }
    }
}
