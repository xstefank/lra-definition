package org.xstefank.lra.execution.model;

import lombok.ToString;
import org.xstefank.lra.definition.LRADefinition;

@ToString
public class LRAResult {

    private final LRAOutcome outcome;
    private final LRADefinition lraDefinition;

    public LRAResult(LRAOutcome outcome, LRADefinition lraDefinition) {
        this.outcome = outcome;
        this.lraDefinition = lraDefinition;
    }

    public LRAOutcome getOutcome() {
        return outcome;
    }

    public LRADefinition getLraDefinition() {
        return lraDefinition;
    }

}
