package com.github.archangel_styx.spells;

import com.github.archangel_styx.spellcosts.ExpCost;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TestLightningCantrip extends CantripSpell {
    public TestLightningCantrip()
    {
        super();
        this.name = "Lightning Spell";
        this.description = "Zap your foes with Thor's fury.";
        this.castSpeed = 100;
        this.costs.add(new ExpCost(10));
    }

    @Override
    public InteractionResult castSpell(Level level, Player user, InteractionHand hand)
    {
        BlockPos frontOfPlayer = user.blockPosition().relative(user.getDirection(), 10);

        // Spawn the lightning bolt.
        LightningBolt lightningBolt = new LightningBolt(EntityTypes.LIGHTNING_BOLT, level);
        lightningBolt.setPos(new Vec3(frontOfPlayer));
        level.addFreshEntity(lightningBolt);

        return InteractionResult.SUCCESS;
    }
}
