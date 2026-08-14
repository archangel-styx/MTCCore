package com.github.archangel_styx.spells;

import net.minecraft.ChatFormatting;

public class ColorHelper {
    public static ChatFormatting getColor(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> ChatFormatting.GREEN;
            case RARE -> ChatFormatting.BLUE;
            case LEGENDARY -> ChatFormatting.LIGHT_PURPLE;
            case UNIQUE -> ChatFormatting.DARK_GREEN;
            default -> ChatFormatting.WHITE;
        };
    }
}
