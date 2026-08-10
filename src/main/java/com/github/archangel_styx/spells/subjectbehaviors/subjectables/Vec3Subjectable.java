package com.github.archangel_styx.spells.subjectbehaviors.subjectables;

import net.minecraft.world.phys.Vec3;

public class Vec3Subjectable implements Subjectable<Vec3> {
    private final Vec3 subject;

    public Vec3Subjectable(Vec3 subject) {
        this.subject = subject;
    }

    public Vec3 get()
    {
        return subject;
    }
}
