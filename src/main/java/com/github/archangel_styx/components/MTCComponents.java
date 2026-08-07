package com.github.archangel_styx.components;

import com.github.archangel_styx.MTCCore;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import java.sql.DatabaseMetaData;
import java.util.List;

public class MTCComponents {
    public static void initialize() {}

    public static final DataComponentType<String> ACTIVE_SPELL = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MTCCore.MOD_ID, "active_spell"),
            DataComponentType.<String>builder().persistent(Codec.STRING).build()
    );

    public static final DataComponentType<List<String>> SPELL_LIST = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            Identifier.fromNamespaceAndPath(MTCCore.MOD_ID, "spell_list"),
            DataComponentType.<List<String>> builder().persistent(Codec.STRING.listOf()).build()
    );

    public static final DataComponentType<Boolean> FOIL = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            MTCCore.id("foil"),
            DataComponentType.<Boolean>builder().persistent(Codec.BOOL).build()
    );
}
