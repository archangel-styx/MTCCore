package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.EntitySubjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Vec3Subjectable;
import com.github.archangel_styx.util.RayCasting;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class TargetEntityOrBlock implements SubjectBehavior {

    private final Integer range;

    public TargetEntityOrBlock(Integer range)
    {
        this.range = range;
    }

    public Subjectable<?> getSubject(WorldContext context) {
        BlockHitResult blockResult = RayCasting.getBlock(context, this.range);
        EntityHitResult entityResult = RayCasting.getEntity(context, this.range);

        if (entityResult != null) {
            return new EntitySubjectable(entityResult.getEntity());
        }
        else
        {
            return new Vec3Subjectable(blockResult.getLocation());
        }
    }
}
