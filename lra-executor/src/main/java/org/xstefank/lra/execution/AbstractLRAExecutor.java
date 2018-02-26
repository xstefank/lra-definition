package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;
import org.xstefank.lra.model.Result;

import java.net.URL;

@SuppressWarnings(value = "unchecked")
public abstract class AbstractLRAExecutor<T extends Action> implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    @Override
    public void executeLRA(LRADefinition lraDefinition) {
        log.infof("Processing LRA %s", lraDefinition);

        URL lraUrlId = startLRA(lraDefinition);

        String lraId = startLRA(lraDefinition).toString();
        LRAData data = new LRAData(lraId, lraDefinition.getData());

        boolean needCompensation = lraDefinition.getActions().stream()
                .map(a -> executeAction((T) a, data))
                .anyMatch(x -> ((ActionResult) x).getResult().equals(Result.FAILURE));

        if (!needCompensation) {
            completeLRA();
        } else {
            compensateLRA();
        }

    }

    protected ActionResult executeAction(T action, LRAData data) {
        return action.invoke(data);
    }

    public abstract URL startLRA(LRADefinition lra);

    protected abstract void compensateLRA();

    protected abstract void completeLRA();
}
