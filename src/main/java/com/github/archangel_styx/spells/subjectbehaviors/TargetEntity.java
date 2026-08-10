package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.EntitySubjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import com.github.archangel_styx.util.RayCasting;
import net.minecraft.world.phys.EntityHitResult;

public class TargetEntity implements SubjectBehavior {
    private final Integer range;

    public TargetEntity(Integer range)
    {
        this.range = range;
    }

    public Subjectable<?> getSubject(WorldContext context) {
        EntityHitResult entityResult = RayCasting.getEntity(context, range);

        if (entityResult != null) {
            return new EntitySubjectable(entityResult.getEntity());
        }
        else
        {
            return null;
        }
    }
}
