package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Vec3Subjectable;
import com.github.archangel_styx.util.RayCasting;
import com.github.archangel_styx.util.WorldContext;

public class FreeAim implements SubjectBehavior {
    private final int range;

    public FreeAim(int range)
    {
        this.range = range;
    }

    public Subjectable<?> getSubject(WorldContext context) {
        return new Vec3Subjectable(RayCasting.getBlock(context.getPlayer(), range).getLocation());
    }
}
