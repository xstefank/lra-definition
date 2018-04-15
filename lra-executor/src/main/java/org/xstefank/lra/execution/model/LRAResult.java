package org.xstefank.lra.execution.model;

import lombok.ToString;
import org.xstefank.lra.definition.LRADefinition;

import java.net.URL;

@ToString
public class LRAResult {

    private URL lraId;
    private final LRAOutcome outcome;
    private final LRADefinition lraDefinition;

    public LRAResult(URL lraId, LRAOutcome outcome, LRADefinition lraDefinition) {
        this.lraId = lraId;
        this.outcome = outcome;
        this.lraDefinition = lraDefinition;
    }

    public URL getLraId() {
        return lraId;
    }

    public LRAOutcome getOutcome() {
        return outcome;
    }

    public LRADefinition getLraDefinition() {
        return lraDefinition;
    }

}
