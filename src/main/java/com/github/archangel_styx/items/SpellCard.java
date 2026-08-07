package com.github.archangel_styx.items;

import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.Spell;
import com.github.archangel_styx.spells.Spells;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Random;

public class SpellCard extends SpellCasterItem {
    public SpellCard(Properties props) {
        super(props);
    }

    public void foilChance(ItemStack stack, Player player) {
        if (!player.level().isClientSide() && new Random().nextInt(9) == 0) {
            stack.set(MTCComponents.FOIL, true);
            stack.set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
        }
    }

    @Override
    public void onCraftedBy(ItemStack stack, Player player) {
        foilChance(stack, player);
        super.onCraftedBy(stack, player);
    }


    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.PASS;
        }

        ItemStack stack = user.getItemInHand(hand);
        activeSpell = stack.get(MTCComponents.ACTIVE_SPELL);
        Spell spell = Spells.REGISTRY.get(activeSpell);

        InteractionResult result = spell.castSpell(level, user, hand);

        if (result == InteractionResult.PASS && stack.isDamageableItem() && !user.isCreative()) {
            ItemUtil.damage(stack);
        }

        return result;
    }
}
