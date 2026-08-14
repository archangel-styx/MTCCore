package com.github.archangel_styx.spells;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TextColor;

public class ColorHelper {
    public static TextColor getColor(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> TextColor.GREEN;
            case RARE -> TextColor.fromRgb(51967);
            case LEGENDARY -> TextColor.fromRgb(4276833);
            case UNIQUE -> TextColor.fromRgb(39800);
        };
    }
}
