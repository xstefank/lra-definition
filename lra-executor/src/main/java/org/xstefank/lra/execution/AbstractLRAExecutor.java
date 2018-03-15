package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;

import java.net.URL;

@SuppressWarnings(value = "unchecked")
public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    @Override
    public void executeLRA(LRADefinition lraDefinition) {
        log.infof("Processing LRA with definition: ", lraDefinition);

        log.info("Starting LRA...");
        URL lraId = startLRA(lraDefinition);
        log.info("started LRA: " + lraId);

        LRAData data = new LRAData(lraId, lraDefinition.getData());
        boolean needCompensation = false;

        log.info("Executing LRA...");
        for (Action action : lraDefinition.getActions()) {
            ActionResult result = executeAction(action, data);
            if (result.isFailure()) {
                needCompensation = true;
                break;
            }
        }

        if (!needCompensation) {
            completeLRA(lraId);
        } else {
            compensateLRA(lraId);
        }

    }

    protected ActionResult executeAction(Action action, LRAData data) {
        return action.invoke(data);
    }

    protected abstract URL startLRA(LRADefinition lraDefinition);

    protected abstract void compensateLRA(URL lraId);

    protected abstract void completeLRA(URL lraId);
}
