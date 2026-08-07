package com.github.archangel_styx.items;

import com.github.archangel_styx.spells.Spell;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SpellCasterItem extends Item {

    protected Spell spell;

    public SpellCasterItem(Properties props, Spell spell) {
        super(props);
        this.spell = spell;
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand)
    {
        if (level.isClientSide())
        {
            return InteractionResult.PASS;
        }

        return this.spell.castSpell(level, user, hand);
    }
}
