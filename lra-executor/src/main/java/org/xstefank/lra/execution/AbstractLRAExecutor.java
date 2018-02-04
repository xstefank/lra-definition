package org.xstefank.lra.execution;

import io.narayana.lra.client.GenericLRAException;
import io.narayana.lra.client.LRAClient;
import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.model.ActionResult;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Stateless
public abstract class AbstractLRAExecutor implements LRAExecutor {

    private static final Logger log = Logger.getLogger(AbstractLRAExecutor.class);

    @Inject
    private LRAClient lraClient;

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

    private URL startLRA(String baseUri) {
        String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
        URL lraUrlId = lraClient.startLRA(null, AbstractLRAExecutor.class.getName() + "#"
                + methodName, 0L, TimeUnit.SECONDS);

        String recoveryPath = lraClient.joinLRA(lraUrlId, 0L, baseUri, null);
        log.infof("Starting LRA: %s when joining with baseUri: %s on enlistment gets recovery path: %s",
                lraUrlId, baseUri, recoveryPath);
        return lraUrlId;
    }

    protected ActionResult executeAction(Action action, Object data) {
        return action.invoke(data);
    }

    protected abstract void compensateLRA();

    protected abstract void completeLRA();
}
