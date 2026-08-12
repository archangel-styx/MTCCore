package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.util.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.EntitySubjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Vec3Subjectable;
import com.github.archangel_styx.util.RayCasting;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class TargetEntityOrBlock implements SubjectBehavior {
    private final int blockRange;
    private final int entityRange;

    public TargetEntityOrBlock(int blockRange, int entityRange)
    {
        this.blockRange = blockRange;
        this.entityRange = entityRange;
    }

    public Subjectable<?> getSubject(WorldContext context) {
        BlockHitResult blockResult = RayCasting.getBlock(context.getPlayer(), this.blockRange);
        EntityHitResult entityResult = RayCasting.getEntity(context.getPlayer(), this.entityRange);

        if (entityResult != null) {
            return new EntitySubjectable(entityResult.getEntity());
        }
        else
        {
            return new Vec3Subjectable(blockResult.getLocation());
        }
    }
}
