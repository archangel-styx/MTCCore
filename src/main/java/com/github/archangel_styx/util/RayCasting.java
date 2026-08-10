package com.github.archangel_styx.util;

import com.github.archangel_styx.WorldContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.CollisionContext;

public class RayCasting {

    public static BlockHitResult getBlock(WorldContext context, Integer range) {
        Player player = context.getPlayer();
        Vec3 eyePos = player.getEyePosition(1f);
        Vec3 lookAngle = player.getLookAngle();
        Vec3 endPos = eyePos.add(lookAngle.scale(range));

        return player.level().clip(new ClipContext(
                eyePos,
                endPos,
                ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,
                CollisionContext.of(player)
        ));
    }

    public static EntityHitResult getEntity(WorldContext context, Integer range) {
        Player player = context.getPlayer();
        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookAngle = player.getLookAngle();

        BlockHitResult blockResult = getBlock(context, range);

        double maxDist = range;
        double blockDist = eyePos.distanceTo(blockResult.getLocation());
        if (blockDist < maxDist) {
            maxDist = blockDist;
        }

        EntityHitResult result = castRay(player, eyePos, eyePos.add(lookAngle.scale(maxDist)));

        if (result == null)
        {
            float spread = 0.05f;
            result = castRay(player, eyePos, eyePos.add(lookAngle.xRot(spread).scale(maxDist)));
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.xRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(spread).xRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(spread).xRot(-spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(-spread).xRot(spread).scale(maxDist))); }
            if (result == null) { result = castRay(player, eyePos, eyePos.add(lookAngle.yRot(-spread).xRot(-spread).scale(maxDist))); }
        }


        return result;
    }

    private static EntityHitResult castRay(Player player, Vec3 eyePos, Vec3 targetPos) {
        return ProjectileUtil.getEntityHitResult(
                player,
                eyePos,
                targetPos,
                player.getBoundingBox().expandTowards(targetPos.subtract(eyePos)).inflate(1.0),
                Entity::canBeHitByProjectile,
                0.0
        );
    }
}
