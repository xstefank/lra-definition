package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.execution.model.LRAOutcome;
import org.xstefank.lra.execution.model.LRAResult;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings(value = "unchecked")
public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public LRAResult executeLRA(LRADefinition lraDefinition) {
        return execute(lraDefinition);
    }

    /**
     * The default async implemetation submits the execution to separate thread that calls
     * startLRA(LRADefinition) method which in that case may be invoked by several threads concurrently
     *
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    @Override
    public Future<LRAResult> executeLRAAsync(LRADefinition lraDefinition) {
        return executorService.submit(() -> execute(lraDefinition));
    }

    private LRAResult execute(LRADefinition lraDefinition) {
        log.infof("Processing LRA with definition: " + lraDefinition);

        URL lraId = startLRA(lraDefinition);
        log.info("started LRA: " + lraId);

        LRAData data = new LRAData(lraId, lraDefinition.getData());
        LRAResult lraResult = null;

        log.info("Executing LRA...");
        for (Action action : lraDefinition.getActions()) {
            ActionResult result = executeAction(action, data);

            if (result.isFailure()) {
                lraResult = new LRAResult(lraId, LRAOutcome.NEED_COMPENSATION, lraDefinition,
                        result.getMessage(), result.getCause());
                break;
            }
        }

        if (lraResult == null) {
            lraResult = new LRAResult(lraId, LRAOutcome.COMPLETED, lraDefinition);
            completeLRA(lraResult);
            return lraResult;
        } else {
            compensateLRA(lraResult);
            return lraResult;
        }
    }

    protected ActionResult executeAction(Action action, LRAData data) {
        return action.invoke(data);
    }

    protected abstract void completeLRA(LRAResult result);

    protected abstract void compensateLRA(LRAResult result);
}
