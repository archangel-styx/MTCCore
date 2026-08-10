package com.github.archangel_styx;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WorldContext {
    private final Level world;
    private final Player player;
    private final InteractionHand hand;

    public WorldContext(Level world, Player player, InteractionHand hand)
    {
            this.world = world;
            this.player = player;
            this.hand = hand;
    }

    public Level getWorld() {
        return this.world;
    }

    public Player getPlayer()
    {
        return this.player;
    }

    public InteractionHand getHand()
    {
        return this.hand;
    }
}
