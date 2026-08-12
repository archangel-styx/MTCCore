package com.github.archangel_styx.spells;

import com.github.archangel_styx.util.WorldContext;
import com.github.archangel_styx.spells.costs.SpellCostable;
import com.github.archangel_styx.spells.castbehaviors.CastBehavior;
import com.github.archangel_styx.spells.subjectbehaviors.SubjectBehavior;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import net.minecraft.world.InteractionResult;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Spell {
    private final String name;
    private final String description;
    private final SpellType spellType;
    private final Rarity rarity;
    private final CastBehavior castBehavior;
    private final SubjectBehavior subjectBehavior;
    private final long speed;
    private final float cooldown;

    private final List<SpellCostable> costs;
    private final List<Function<SpellContext, InteractionResult>> effects;

    private Spell(Builder b) {
        this.name = b.name;
        this.description = b.description;
        this.spellType = b.spellType;
        this.rarity = b.rarity;
        this.castBehavior = b.castBehavior;
        this.subjectBehavior = b.subjectBehavior;
        this.costs = b.costs;
        this.effects = b.effects;
        this.speed = b.speed;
        this.cooldown = b.cooldown;
    }

    private InteractionResult iterateEffects(SpellContext context)
    {
        effects.forEach((callback) -> {
            callback.apply(context);
        });

        return InteractionResult.PASS;
    }

    public InteractionResult castSpell(WorldContext context)
    {
        Subjectable<?> subject = subjectBehavior.getSubject(context);
        return this.castBehavior.cast(new SpellContext(context, subject, this), this::iterateEffects);
    }

    public List<SpellCostable> getCosts() {
        return this.costs;
    }

    public String getDescription() { return this.description; }

    public float getCooldown() {
        return this.cooldown;
    }

    public long getSpeed() { return this.speed; }

    public static class Builder {
        private final String name;
        private final String description;
        private Rarity rarity;
        private SpellType spellType;
        private CastBehavior castBehavior;
        private SubjectBehavior subjectBehavior;
        private List<SpellCostable> costs = new ArrayList<>();
        private final List<Function<SpellContext, InteractionResult>> effects = new ArrayList<>();
        private long speed = 0;
        private float cooldown = 1.0F;

        public Builder(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Builder rarity(Rarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder spellType(SpellType spellType) {
            this.spellType = spellType;
            return this;
        }

        public Builder castBehavior(CastBehavior castBehavior) {
            this.castBehavior = castBehavior;
            return this;
        }

        public Builder subjectBehavior(SubjectBehavior subjectBehavior) {
            this.subjectBehavior = subjectBehavior;
            return this;
        }

        public Builder costs(List<SpellCostable> costs) {
            this.costs = costs;
            return this;
        }

        public Builder addCost(SpellCostable cost)
        {
            this.costs.add(cost);
            return this;
        }

        public Builder addStep(Function<SpellContext, InteractionResult> factory)
        {
            this.effects.add(factory);
            return this;
        }
        /** @param castingSpeed in game ticks. */
        public Builder speed(long castingSpeed) {
            this.speed = castingSpeed;
            return this;
        }

        public Builder cooldown(float cooldown) {
            this.cooldown = cooldown;
            return this;
        }

        public Spell build()
        {
            return new Spell(this);
        }
    }

}

