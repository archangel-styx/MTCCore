package com.github.archangel_styx.spells;

import net.minecraft.world.InteractionResult;

import java.util.function.Function;

public class SpellEvent {
    private final Function<SpellContext, InteractionResult> callback;
    private final SpellContext context;
    private long ticks;
    public SpellEvent(Function<SpellContext, InteractionResult> callback, SpellContext context, long ticks) {
        this.callback = callback;
        this.context = context;
        this.ticks = ticks;
    }

    public void run() {
        this.callback.apply(context);
    }

    public long ticks() {
        return ticks;
    }

    public void tick()
    {
        this.ticks--;
    }
}
