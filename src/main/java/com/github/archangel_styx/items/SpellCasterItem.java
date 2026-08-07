package com.github.archangel_styx.items;

import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.Spell;
import com.github.archangel_styx.spells.Spells;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpellCasterItem extends Item {

    protected String activeSpell;

    public SpellCasterItem(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand)
    {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        ItemStack stack =  user.getItemInHand(hand);
        activeSpell = stack.get(MTCComponents.ACTIVE_SPELL);
        Spell spell = Spells.REGISTRY.get(activeSpell);

        InteractionResult result = spell.castSpell(level,user, hand);

        return result;
    }
}
