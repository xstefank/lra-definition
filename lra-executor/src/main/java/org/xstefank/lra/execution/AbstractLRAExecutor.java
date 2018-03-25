package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;
import org.xstefank.lra.model.LRAOutcome;
import org.xstefank.lra.model.LRAResult;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings(value = "unchecked")
public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    private ExecutorService executorService = Executors.newFixedThreadPool(3);

    @Override
    public LRAResult executeLRA(LRADefinition lraDefinition) {
        return execute(lraDefinition);
    }

    /**
     * The default async implemetation submits the exectution to separate thread that calls
     * startLRA(LRADefinition) method which in that case may be invocated concurrently by several threads
     *
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    @Override
    public Future<LRAResult> executeLRAAsync(LRADefinition lraDefinition) {
        return executorService.submit(() -> execute(lraDefinition));
    }

    private LRAResult execute(LRADefinition lraDefinition) {
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
            return new LRAResult(LRAOutcome.COMPLETED, lraDefinition);
        } else {
            compensateLRA(lraId);
            return new LRAResult(LRAOutcome.COMPENSATED, lraDefinition);
        }
    }

    protected ActionResult executeAction(Action action, LRAData data) {
        return action.invoke(data);
    }

    protected abstract void compensateLRA(URL lraId);

    protected abstract void completeLRA(URL lraId);
}
