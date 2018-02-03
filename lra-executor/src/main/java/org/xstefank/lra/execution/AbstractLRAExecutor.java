package org.xstefank.lra.execution;

import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.LRAResult;

public abstract class AbstractLRAExecutor implements LRAExecutor {

    @Override
    public void executeLRA(LRADefinition lraDefinition) {
        boolean needCompensation = lraDefinition.getActions().stream()
                .map(a -> executeAction(a, lraDefinition.getData()))
                .anyMatch(x -> x.equals(LRAResult.FAILURE));

        if (!needCompensation) {
            completeLRA();
        } else {
            compensateLRA();
        }
    }

    protected LRAResult executeAction(Action action, Object data) {
        return action.invoke(data);
    }

    protected abstract void compensateLRA();

    protected abstract void completeLRA();
}
