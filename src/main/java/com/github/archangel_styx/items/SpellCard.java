package com.github.archangel_styx.items;

import com.github.archangel_styx.components.MTCComponents;
import com.github.archangel_styx.spells.ColorHelper;
import com.github.archangel_styx.spells.Spell;
import com.github.archangel_styx.spells.Spells;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemLore;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Random;

public class SpellCard extends SpellCasterItem {
    public SpellCard(Properties props, String spellKey) {
        Spell spell = Spells.REGISTRY.get(spellKey);
        props.durability(1000);
        props.component(DataComponents.LORE, new ItemLore(List.of(Component.literal(spell.getDescription()))));
        props.component(MTCComponents.ACTIVE_SPELL, spellKey);
        super(props);
        activeSpell = spellKey;
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
    public @NonNull Component getName(ItemStack itemStack) {
        Spell spell = Spells.REGISTRY.get(activeSpell);
        return Component.translatable("spell." + activeSpell).setStyle(Style.EMPTY.withColor(ColorHelper.getColor(spell.getRarity())).withItalic(false));
    }
}
