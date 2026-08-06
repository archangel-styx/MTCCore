package com.github.archangel_styx.spells;

import com.github.archangel_styx.spellcosts.ExpCost;
import com.github.archangel_styx.spellcosts.HealthCost;
import com.github.archangel_styx.spellcosts.ItemCost;
import com.github.archangel_styx.spellcosts.SpellCostable;
import net.minecraft.world.entity.player.Player;

import java.util.List;

public class SpellUtil {
    public void applyCosts(Spell spell, Player player) {
        List<SpellCostable> costs = spell.getCosts();

        for (SpellCostable cost : costs)
        {
            if (cost.getClass() == ItemCost.class)
            {
            }

            if (cost.getClass() == HealthCost.class)
            {
            }

            if (cost.getClass() == ExpCost.class)
            {
            }
        }
    }
}
