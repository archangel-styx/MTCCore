package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SpellEventScheduler implements ServerTickEvents.EndLevelTick {
    private static final List<SpellEvent> events = new ArrayList<>();

    public static void schedule(Function<SpellContext, InteractionResult> callback, SpellContext context, long ticks)
    {
        events.add(
                new SpellEvent(callback, context, ticks)
        );
    }

    public static void initialize()
    {
        MTCCore.LOGGER.info("Initializing SpellEventScheduler...");
        ServerTickEvents.END_LEVEL_TICK.register(new SpellEventScheduler());
        MTCCore.LOGGER.info("SpellEventScheduler initialized!");
    }

    @Override
    public void onEndTick(@NonNull ServerLevel level) {
        for (int i = 0; i < events.size(); i++)
        {
            SpellEvent e = events.get(i);
            if (e == null) return;
            if (e.ticks() <= 0 )
            {
                e.run();
                events.remove(e);
            }
            else
            {
                e.tick();
            }
        }
    }
}
