package com.github.archangel_styx.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.CollisionContext;

public class RayCasting {

    public static BlockHitResult getBlock(Entity looker, Integer range) {
        Vec3 eyePos = looker.getEyePosition(1f);
        Vec3 lookAngle = looker.getLookAngle();
        Vec3 endPos = eyePos.add(lookAngle.scale(range));

        return looker.level().clip(new ClipContext(
                eyePos,
                endPos,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                CollisionContext.of(looker)
        ));
    }

    public static EntityHitResult getEntity(Entity looker, Integer range) {
        Vec3 eyePos = looker.getEyePosition(1.0F);
        Vec3 lookAngle = looker.getLookAngle();

        BlockHitResult blockResult = getBlock(looker, range);

        double maxDist = range;
        double blockDist = eyePos.distanceTo(blockResult.getLocation());
        if (blockDist < maxDist) {
            maxDist = blockDist;
        }

        EntityHitResult result = castRay(looker, eyePos, eyePos.add(lookAngle.scale(maxDist)));

        if (result == null)
        {
            float spread = 0.05f;
            result = castRay(looker, eyePos, eyePos.add(lookAngle.xRot(spread).scale(maxDist)));
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.xRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(spread).xRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(spread).xRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(-spread).xRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(looker, eyePos, eyePos.add(lookAngle.yRot(-spread).xRot(-spread).scale(maxDist))); }
        }

        return result;
    }

    private static EntityHitResult castRay(Entity looker, Vec3 eyePos, Vec3 targetPos) {
        return ProjectileUtil.getEntityHitResult(
                looker,
                eyePos,
                targetPos,
                looker.getBoundingBox().expandTowards(targetPos.subtract(eyePos)).inflate(5.0),
                Entity::canBeHitByProjectile,
                0.0
        );
    }
}
