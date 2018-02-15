package org.xstefank.lra.execution;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.model.ActionResult;

public class RestLRAExecutor extends AbstractLRAExecutor {

    private static final Logger log = Logger.getLogger(RestLRAExecutor.class);

    @Override
    protected ActionResult executeAction(Action action, String lraId, Object data) {
        return null;
    }

    @Override
    protected void completeLRA() {

    }

    @Override
    protected void compensateLRA() {

    }
}
