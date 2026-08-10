package com.github.archangel_styx.spells.subjectbehaviors;

import com.github.archangel_styx.WorldContext;
import com.github.archangel_styx.spells.subjectbehaviors.subjectables.Subjectable;

public interface SubjectBehavior {
    public Subjectable<?> getSubject(WorldContext context);
}
