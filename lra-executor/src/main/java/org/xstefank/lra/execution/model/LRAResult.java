package org.xstefank.lra.execution.model;

import lombok.ToString;
import org.xstefank.lra.definition.LRADefinition;

import java.net.URL;

@ToString
public class LRAResult {

    private URL lraId;
    private final LRAOutcome outcome;
    private final LRADefinition lraDefinition;

    private final String message;
    private final Throwable cause;

    public LRAResult(URL lraId, LRAOutcome outcome, LRADefinition lraDefinition, String message, Throwable cause) {
        this.lraId = lraId;
        this.outcome = outcome;
        this.lraDefinition = lraDefinition;
        this.message = message;
        this.cause = cause;
    }

    public LRAResult(URL lraId, LRAOutcome outcome, LRADefinition lraDefinition, String message) {
        this(lraId, outcome, lraDefinition, message, null);
    }

    public LRAResult(URL lraId, LRAOutcome outcome, LRADefinition lraDefinition) {
        this(lraId, outcome, lraDefinition, null, null);
    }

    public URL getLraId() {
        return lraId;
    }

    public LRAOutcome getOutcome() {
        return outcome;
    }

    public boolean isSuccess() {
        return outcome.equals(LRAOutcome.COMPLETED);
    }

    public LRADefinition getLraDefinition() {
        return lraDefinition;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
