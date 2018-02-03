package org.xstefank.lra.execution;

import org.xstefank.lra.definition.Action;
import org.xstefank.lra.model.LRAResult;

public class RestLRAExecutor extends AbstractLRAExecutor {


    @Override
    protected LRAResult executeAction(Action action, Object data) {
        return null;
    }

    @Override
    protected void compensateLRA() {

    }

    @Override
    protected void completeLRA() {

    }
}
