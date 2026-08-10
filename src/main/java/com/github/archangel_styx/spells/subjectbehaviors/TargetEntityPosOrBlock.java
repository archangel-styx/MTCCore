package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Vec3Subjectable;
import com.github.archangel_styx.util.RayCasting;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class TargetEntityPosOrBlock implements SubjectBehavior {
    private final Integer range;

    public TargetEntityPosOrBlock(Integer range)
    {
        this.range = range;
    }

    public Subjectable<?> getSubject(WorldContext context) {
        BlockHitResult blockResult = RayCasting.getBlock(context, this.range);
        EntityHitResult entityResult = RayCasting.getEntity(context, this.range);


        if (entityResult != null) {
            return new Vec3Subjectable(entityResult.getEntity().position());
        }
        else
        {
            return new Vec3Subjectable(blockResult.getLocation());
        }
    }
}
