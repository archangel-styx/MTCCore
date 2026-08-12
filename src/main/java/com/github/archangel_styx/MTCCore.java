package com.github.archangel_styx;

import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.items.MTCItems;
import com.github.archangel_styx.spells.Spells;
import com.github.archangel_styx.spells.SpellEventScheduler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MTCCore implements ModInitializer {
	public static final String MOD_ID = "mtccore";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		MTCItems.initialize();
		MTCComponents.initialize();
		Spells.initialize();
		SpellEventScheduler.initialize();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
