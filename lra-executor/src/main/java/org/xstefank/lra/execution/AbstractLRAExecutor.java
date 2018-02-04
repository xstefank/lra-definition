package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;

public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    @Override
    public void executeLRA(LRADefinition lraDefinition) {
        log.infof("Processing LRA %s", lraDefinition);

        //TODO let subclasses start LRA?
//        URL lraUrlId = startLRA(baseUri);
//        Object info = lra.getInfo();

        boolean needCompensation = lraDefinition.getActions().stream()
                .map(a -> executeAction(a, lraDefinition.getData()))
                .anyMatch(x -> x.equals(ActionResult.FAILURE));

        if (!needCompensation) {
            completeLRA();
        } else {
            compensateLRA();
        }

    }

    protected ActionResult executeAction(Action action, Object data) {
        return action.invoke(data);
    }

    protected abstract void compensateLRA();

    protected abstract void completeLRA();
}
