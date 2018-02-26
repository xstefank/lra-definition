package io.narayana.lra.coordinator.executor;

import io.narayana.lra.client.Current;
import io.narayana.lra.client.NarayanaLRAClient;
import io.narayana.lra.coordinator.domain.service.LRAService;
import org.jboss.logging.Logger;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.execution.AbstractLRAExecutor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URL;

import static io.narayana.lra.client.NarayanaLRAClient.COORDINATOR_PATH_NAME;

@ApplicationScoped
public class RestLRAExecutor extends AbstractLRAExecutor {

    private static final Logger log = Logger.getLogger(RestLRAExecutor.class);

    private final URI coordinatorURI;
    private LRAService lraService;

    public RestLRAExecutor(URI coordinatorURI, LRAService lraService) {
        this.coordinatorURI = coordinatorURI;
        this.lraService = lraService;
    }

    @Override
    public URL startLRA(LRADefinition lra) {
        URL parentLRAUrl = null;
        String parentLRA = lra.getParentLRA();

        if (parentLRA != null && !parentLRA.isEmpty())
            parentLRAUrl = NarayanaLRAClient.lraToURL(parentLRA, "Invalid parent LRA id");

        String coordinatorUrl = String.format("%s%s", coordinatorURI, COORDINATOR_PATH_NAME);
        URL lraId = lraService.startLRA(coordinatorUrl, parentLRAUrl, lra.getClientId(), lra.getTimeout());

        //TODO nested join
//        if (parentLRAUrl != null) {
//            // register with the parentLRA as a participant
//            Client client = ClientBuilder.newClient();
//            String compensatorUrl = String.format("%s/%s", coordinatorUrl,
//                    NarayanaLRAClient.encodeURL(lraId, "Invalid parent LRA id"));
//            Response response;
//
//            if (lraService.hasTransaction(parentLRAUrl))
//                response = joinLRAViaBody(parentLRAUrl.toExternalForm(), timelimit, null, compensatorUrl);
//            else
//                response = client.target(parentLRA).request().put(Entity.text(compensatorUrl));
//
//            if (response.getStatus() != Response.Status.OK.getStatusCode())
//                return response;
//        }

        Current.push(lraId);

        return lraId;
    }

    @Override
    protected void compensateLRA() {

    }

    @Override
    protected void completeLRA() {

    }
}
