package com.github.archangel_styx.spells;

import com.github.archangel_styx.util.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SpellContext {
    private final WorldContext worldContext;
    private final Subjectable<?> subjectable;
    private final Spell spell;
    public SpellContext(WorldContext world, Subjectable<?> subject, Spell spell)
    {
        this.worldContext = world;
        this.subjectable = subject;
        this.spell = spell;
    }

    public Player player() {
        return this.worldContext.getPlayer();
    }

    public Level level() {
        return this.worldContext.getWorld();
    }

    public Subjectable<?> subject() {
        return this.subjectable;
    }

    public InteractionHand hand() {
        return this.worldContext.getHand();
    }

    public Spell spell() {
        return this.spell;
    }
}
