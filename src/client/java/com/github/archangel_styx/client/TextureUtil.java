package com.github.archangel_styx.client;

import net.minecraft.client.Minecraft;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;

public class TextureUtil {
    public static void applyFallbackTexture(ItemStack stack, Identifier textureId, Identifier backupTextureId)
    {
        Minecraft client = Minecraft.getInstance();

        if (client == null || client.getResourceManager() == null) {
            return;
        }
        ResourceManager resourceManager = client.getResourceManager();

        boolean textureExists = resourceManager.getResource(textureId) != null;

        if (!textureExists) {
            stack.set(DataComponents.ITEM_MODEL, backupTextureId);
        }
    }
}
