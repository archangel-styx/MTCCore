package com.github.archangel_styx.spells.subjectbehaviors.subjectables;

import net.minecraft.world.entity.Entity;

public class EntitySubjectable implements Subjectable<Entity> {
    private final Entity subject;

    public EntitySubjectable(Entity subject) {
        this.subject = subject;
    }

    public Entity get() {
        return subject;
    }
}
