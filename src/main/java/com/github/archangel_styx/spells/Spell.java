package com.github.archangel_styx.spells;

import com.github.archangel_styx.spellcosts.SpellCostable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class Spell {
    protected String name;
    protected String description;
    protected Integer castSpeed;
    protected SpellType spellType;
    protected Rarity rarity;
    protected boolean isFoil;

    protected final List<SpellCostable> costs = new ArrayList<>();

    public InteractionResult castSpell(Level world, Player player, InteractionHand hand)
    {
        return InteractionResult.PASS;
    }

    public List<SpellCostable> getCosts()
    {
        return this.costs;
    }

    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
}