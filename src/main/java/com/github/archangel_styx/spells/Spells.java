package com.github.archangel_styx.spells;

import com.github.archangel_styx.spells.castbehaviors.ChargeBehavior;
import com.github.archangel_styx.spells.castbehaviors.HoldBehavior;
import com.github.archangel_styx.spells.costs.ExpCost;
import com.github.archangel_styx.spells.castbehaviors.ClickBehavior;
import com.github.archangel_styx.spells.subjectbehaviors.FreeAim;
import com.github.archangel_styx.spells.subjectbehaviors.SubjectSelf;
import com.github.archangel_styx.spells.subjectbehaviors.TargetEntityOrBlock;
import com.github.archangel_styx.spells.subjectbehaviors.TargetEntityPosOrBlock;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.EntitySubjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.core.jmx.Server;

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
            .addCost(new ExpCost(2))
            .castBehavior(new HoldBehavior())
            .cooldown(10F)
            .subjectBehavior(new SubjectSelf())
            .rarity(Rarity.RARE)
            .addStep((context) ->
            {
                Player player = (Player) context.subject().get();
                player.addEffect(
                        new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false, false)
                );
                return InteractionResult.PASS;
            })
            .build());
    public static final Spell SUMMON_LIGHTNING_CANTRIP = register(SpellKeys.SUMMON_LIGHTNING, new Spell.Builder(
                    "Summon Lightning",
                    "Summon forth the power of Thor.")
                    .addCost(new ExpCost(200))
                    .castBehavior(new ClickBehavior())
                    .subjectBehavior(new TargetEntityPosOrBlock(32, 52))
                    .speed(23)
                    .addStep((context) ->
                    {
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
    public static final Spell SPIT_FIRE_CONTINUOUS = register(SpellKeys.SPITFIRE, new Spell.Builder(
            "Spitfire",
            "Burn it all down.")
            .subjectBehavior(new FreeAim(12))
            .castBehavior(new HoldBehavior())
            .cooldown(1F)
            .speed(3)
            .addCost(new ExpCost(1))
            .rarity(Rarity.COMMON)
            .addStep((context) -> {
                if (context.level().isClientSide())
                {
                    return InteractionResult.PASS;
                }

                ServerLevel level = (ServerLevel)context.level();
                Player player = context.player();
                Vec3 eyePos = player.getEyePosition();
                Vec3 endPos = (Vec3) context.subject().get();
                AABB hitBox = new AABB(eyePos, endPos).inflate(0.2F);
                List<Entity> entities = level.getEntities(player, hitBox);
                for (Entity entity : entities)
                {
                    if(entity.canBeHitByProjectile() && !entity.fireImmune())
                    {
                        entity.hurtServer(level, new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).get(DamageTypes.IN_FIRE.identifier()).orElseThrow()), 4.0F);
                    }
                  entity.setRemainingFireTicks(10);
                }

                if (level.getRandom().nextFloat() < 0.2F)
                {
                    BlockPos firePos = new BlockPos((int) Math.floor(endPos.x), (int) Math.floor(endPos.y), (int) Math.floor(endPos.z));
                    level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
                }


                Vec3 line = eyePos.vectorTo(endPos).normalize();
                Vec3 randomLine = line.offsetRandom(level.getRandom(), 1);

                level.sendParticles(ParticleTypes.FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, line.x(), line.y(), line.z(), 0.75);
                level.sendParticles(ParticleTypes.FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, randomLine.x(), randomLine.y() / 2, randomLine.z(), 0.75);
                level.sendParticles(ParticleTypes.SMALL_FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, randomLine.x(), randomLine.y() / 2, line.z(), 0.5);
                level.sendParticles(ParticleTypes.SMALL_FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, line.x(), randomLine.y() / 2, randomLine.z(), 0.5);
                level.sendParticles(ParticleTypes.SMALL_FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, line.x() * 2, randomLine.y() / 2, randomLine.z(), 0.5);
                level.sendParticles(ParticleTypes.SMALL_FLAME, eyePos.x(), eyePos.y() - 0.3, eyePos.z(), 0, line.x(), randomLine.y() / 2, randomLine.z() * 2, 0.5);
                level.playSound(null, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, 0.5f);

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
