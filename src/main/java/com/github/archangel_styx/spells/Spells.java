package com.github.archangel_styx.spells;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.spells.castbehaviors.ChargeBehavior;
import com.github.archangel_styx.spells.costs.ExpCost;
import com.github.archangel_styx.spells.castbehaviors.CantripBehavior;
import com.github.archangel_styx.spells.subjectbehaviors.SubjectSelf;
import com.github.archangel_styx.spells.subjectbehaviors.TargetEntityPosOrBlock;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spells {
    public static final Map<String, Spell> REGISTRY = new HashMap<>();

    public static final Spell THE_HERMIT = register(SpellKeys.THE_HERMIT, new Spell.Builder(
                    "The Hermit",
                    "IX").rarity(Rarity.UNIQUE).build());
    public static final Spell SHROUD_AURA = register(SpellKeys.SHROUD_AURA, new Spell.Builder(
                    "Shrouded Veil",
                    "Like the ocean breeze.")
            .addCost(new ExpCost(333))
            .castBehavior(new CantripBehavior())
            .cooldown(60F)
            .subjectBehavior(new SubjectSelf())
            .rarity(Rarity.RARE)
            .addStep((context) ->
            {
                Player player = (Player) context.subject().get();
                player.addEffect(
                        new MobEffectInstance(MobEffects.INVISIBILITY, 30 * 20, 0, false, false, false)
                );
                return InteractionResult.PASS;
            })
            .build());
    public static final Spell SUMMON_LIGHTNING_CANTRIP = register(SpellKeys.SUMMON_LIGHTNING, new Spell.Builder(
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
    public static final Spell MYSTERY_CARD_CANTRIP = register(SpellKeys.MYSTERY_CARD, new Spell.Builder(
                    "Mystery Card",
                    "Unlimited potential.")
                    .rarity(Rarity.UNIQUE).build());
    public static final Spell ROLLING_THUNDER_CHARGED = register(SpellKeys.ROLLING_THUNDER, new Spell.Builder(
            "Rolling Thunder",
            "Let it rain.")
            .subjectBehavior(new TargetEntityPosOrBlock(20, 22))
            .castBehavior(new ChargeBehavior())
            .cooldown(8F)
            .speed(50)
            .addCost(new ExpCost(500))
            .rarity(Rarity.RARE)
            .addStep((context) ->
            {
                Level level = context.level();
                Vec3 playerPos = context.player().getPosition(0.0F);
                Vec3 endPos = (Vec3) context.subject().get();
                Vec3 line = playerPos.vectorTo(endPos);
                line = new Vec3(line.x, 0, line.z);
                double distance = playerPos.distanceTo(endPos);
                List<Vec3> points = new ArrayList<>();
                RandomSource random = RandomSource.create();
                for (int i = (int) distance / 2; i < distance; i++)
                {
                    points.add(playerPos.add(line.scale(i / distance)).offsetRandomXZ(random, 4));
                }

                points.add(endPos);
                for (int i = 0; i < points.size(); i++)
                {
                    int position = i;
                    SpellEventScheduler.schedule(
                            (c) -> {
                                LightningBolt lightningBolt = new LightningBolt(EntityTypes.LIGHTNING_BOLT, level);
                                lightningBolt.setPos(points.get(position));
                                level.addFreshEntity(lightningBolt);

                                return InteractionResult.PASS;
                            },
                            context,
                            (long) i * 10
                    );
                }

                return InteractionResult.PASS;
            })
            .build());


    public static void initialize()
    {
    }


    public static Spell register(Identifier id, Spell spell)
    {
        REGISTRY.put(id.toString(), spell);
        return spell;
    }
}
