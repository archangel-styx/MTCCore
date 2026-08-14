package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.util.WorldContext;
import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.Spell;
import com.github.archangel_styx.spells.Spells;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class SpellCasterItem extends Item {

    protected String activeSpell;

    public SpellCasterItem(Properties props) {
        super(props);
    }

    @Override
    public boolean releaseUsing(@NonNull ItemStack itemStack, @NonNull Level level, @NonNull LivingEntity entity, int remainingTime) {
        if (entity instanceof Player) {
            activeSpell = itemStack.get(MTCComponents.ACTIVE_SPELL);
            Spell spell = Spells.REGISTRY.get(activeSpell);
                if (spell.castSpell(new WorldContext(level, (Player) entity, entity.getUsedItemHand())) == InteractionResult.SUCCESS) {
                    ((Player) entity).getCooldowns().addCooldown(itemStack, (int) spell.getCooldown() * 20);
                    return true;
                };
                return false;
        }
        return false;
    }

    @Override
    public @NonNull InteractionResult use(Level level, @NonNull Player user, @NonNull InteractionHand hand)
    {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        ItemStack stack =  user.getItemInHand(hand);
        activeSpell = stack.get(MTCComponents.ACTIVE_SPELL);
        Spell spell = Spells.REGISTRY.get(activeSpell);

        InteractionResult result = spell.castSpell(new WorldContext(level, user, hand));

        if (result != InteractionResult.CONSUME && result != InteractionResult.FAIL) {
            user.getCooldowns().addCooldown(stack, (int) spell.getCooldown() * 20);
        }

        return result;
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseTicks) {
        if (!(entity instanceof Player player)) {
            return;
        }
        Spell spell = Spells.REGISTRY.get(stack.get(MTCComponents.ACTIVE_SPELL));
        int ticksCharged = player.getTicksUsingItem();

        if (ticksCharged % 10 == 0 && ticksCharged < spell.getSpeed()) {
            if (!level.isClientSide())
            {
                ((ServerLevel) level).sendParticles(ParticleTypes.ENCHANT, player.getX(), player.getY() + 2, player.getZ(), 10, 0, 0, 0,1.0);}
        }

        if (ticksCharged % 10 == 0 && ticksCharged >= spell.getSpeed()) {
            if (!level.isClientSide()) {
                ((ServerLevel) level).sendParticles(ParticleTypes.ENCHANTED_HIT, player.getX(), player.getY() + 1, player.getZ(), 15, 0.5, 0.5, 0.5,0.0);}
            }
        }
    }
