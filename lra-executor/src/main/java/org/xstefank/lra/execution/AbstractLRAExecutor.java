package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.Result;

public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    @Override
    public void executeLRA(LRADefinition lraDefinition) {
        log.infof("Processing LRA %s", lraDefinition);

        //TODO let subclasses start LRA?
//        URL lraUrlId = startLRA(baseUri);
//        Object info = lra.getInfo();
        String lraId = "stub";

        boolean needCompensation = lraDefinition.getActions().stream()
                .map(a -> executeAction(a, lraId, lraDefinition.getData()))
                .anyMatch(x -> x.getResult().equals(Result.FAILURE));

        if (!needCompensation) {
            completeLRA();
        } else {
            compensateLRA();
        }

    }

    protected ActionResult executeAction(Action action, String lraId, Object data) {
        return action.invoke(lraId, data);
    }

    protected abstract void compensateLRA();

    protected abstract void completeLRA();
}
