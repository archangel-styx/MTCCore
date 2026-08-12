package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.util.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.EntitySubjectable;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;

public class SubjectSelf implements SubjectBehavior {

    public Subjectable<?> getSubject(WorldContext context) {
        return new EntitySubjectable(context.getPlayer());
    }
}
