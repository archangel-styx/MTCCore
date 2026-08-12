package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.spells.costs.ExpCost;
import com.github.archangel_styx.spells.castbehaviors.CantripBehavior;
import com.github.archangel_styx.spells.subjectbehaviors.SubjectSelf;
import com.github.archangel_styx.spells.subjectbehaviors.TargetEntityPosOrBlock;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class Spells {
    public static final Map<String, Spell> REGISTRY = new HashMap<>();

    public static final Spell THE_HERMIT = register(SpellKeys.THE_HERMIT, new Spell.Builder(
                    "The Hermit",
                    "IX").build());
    public static final Spell SHROUD_AURA = register(SpellKeys.SHROUD_AURA, new Spell.Builder(
                    "Shrouded Veil",
                    "Like the ocean breeze.")
            .addCost(new ExpCost(333))
            .castBehavior(new CantripBehavior())
            .cooldown(60F)
            .subjectBehavior(new SubjectSelf())
            .addStep((context) ->
            {
                Player player = (Player) context.subject().get();
                player.addEffect(
                        new MobEffectInstance(MobEffects.INVISIBILITY, 30 * 20, 0, false, false, false)
                );
                return InteractionResult.PASS;
            })
            .build());
    public static final Spell SUMMON_LIGHTNING_CANTRIP = register(SpellKeys.SUMMON_LIGHTNING_CANTRIP, new Spell.Builder(
                    "Summon Lightning",
                    "Summon forth the power of Thor.")
                    .addCost(new ExpCost(100))
                    .castBehavior(new CantripBehavior())
                    .subjectBehavior(new TargetEntityPosOrBlock(32, 52))
                    .speed(23)
                    .addStep((context) -> {
                        Level level = context.level();
                        Vec3 position = (Vec3) context.subject().get();

                        LightningBolt lightningBolt = new LightningBolt(EntityTypes.LIGHTNING_BOLT, level);
                        lightningBolt.setPos(position);
                        level.addFreshEntity(lightningBolt);

                        return InteractionResult.PASS;
                    })
                    .build());
    public static final Spell MYSTERY_CARD_CANTRIP = register(SpellKeys.MYSTERY_CARD_CANTRIP, new Spell.Builder(
                    "Mystery Card",
                    "Unlimited potential.").build());

    public static void initialize()
    {
    }


    public static Spell register(Identifier id, Spell spell)
    {
        REGISTRY.put(id.toString(), spell);
        return spell;
    }
}
