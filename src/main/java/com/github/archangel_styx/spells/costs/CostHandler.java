package com.github.archangel_styx.spells.costs;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.spells.SpellContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CostHandler {
    public static boolean pay(SpellContext context) {
        int failures = 0;
        Player user = context.player();

        if (user.isCreative())
        {
            return true;
        }

        for (var c : context.spell().getCosts())
        {
            switch (c) {
                case ExpCost xp -> {
                    ItemStack stack = user.getItemInHand(context.hand());
                    if (stack.getMaxDamage() >= stack.getDamageValue() + (Integer) c.getCost())
                    {
                        damage(stack, (Integer) c.getCost());
                    }
                    else {
                        failures++;
                    }
                }
                case HealthCost hp -> MTCCore.LOGGER.info("HP COST.");
                case ItemCost item -> MTCCore.LOGGER.info("Item COST.");
                default -> {
                    failures++;
                }
            };
        };

        return failures == 0;
    }

    public static void damage(ItemStack stack, Integer cost)
    {
        int max = stack.getMaxDamage();
        int current = stack.getDamageValue() + cost;

        if (max - current <= cost )
        {
            stack.setDamageValue(max);
        }
        else {
            stack.setDamageValue(current);
        }
    }
}
