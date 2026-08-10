package com.github.archangel_styx.items;

import com.github.archangel_styx.MTCCore;
import com.github.archangel_styx.WorldContext;
import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.Spell;
import com.github.archangel_styx.spells.Spells;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class SpellCard extends SpellCasterItem {
    public SpellCard(Properties props, String spellKey) {
        Spell spell = Spells.REGISTRY.get(spellKey);
        MTCCore.LOGGER.info(spell.toString());
        MTCCore.LOGGER.info(spellKey);
        props.durability(1000);
        props.useCooldown(spell.getCooldown());
        props.component(DataComponents.LORE, new ItemLore(List.of(Component.literal(spell.getDescription()))));
        props.component(MTCComponents.ACTIVE_SPELL, spellKey);
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
        InteractionResult result = spell.castSpell(new WorldContext(level, user, hand));

        return result;
    }
}
