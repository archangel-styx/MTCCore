package com.github.archangel_styx.items;

import net.minecraft.world.item.ItemStack;

public class ItemUtil {
    public static void damage(ItemStack stack)
    {
        stack.setDamageValue(stack.getDamageValue() + 1);
        if (stack.getDamageValue() >= stack.getMaxDamage()) {
            stack.shrink(1);
        }
    }
}
