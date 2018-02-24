package io.narayana.lra.coordinator.executor;

import io.narayana.lra.client.Current;
import io.narayana.lra.client.NarayanaLRAClient;
import io.narayana.lra.coordinator.domain.service.LRAService;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.definition.rest.RESTLra;
import org.xstefank.lra.execution.AbstractLRAExecutor;

import javax.inject.Inject;
import java.net.URL;

import static io.narayana.lra.client.NarayanaLRAClient.COORDINATOR_PATH_NAME;

public class RestLRAExecutor extends AbstractLRAExecutor {

    private String baseUri;

    @Inject
    private LRAService lraService;

    public RestLRAExecutor(String baseUri) {
        this.baseUri = baseUri;
    }

    @Override
    public URL startLRA(LRADefinition lra) {
        URL parentLRAUrl = null;

        String parentLRA = lra.getParentLRA();
        if (parentLRA != null && !parentLRA.isEmpty())
            parentLRAUrl = NarayanaLRAClient.lraToURL(parentLRA, "Invalid parent LRA id");

        String coordinatorUrl = String.format("%s%s", baseUri, COORDINATOR_PATH_NAME);
        URL lraId = lraService.startLRAAsync((RESTLra) lra, coordinatorUrl, parentLRAUrl, lra.getClientId(), lra.getTimeout());

//        if (parentLRAUrl != null) {
//            // register with the parentLRA as a participant
//            Client client = ClientBuilder.newClient();
//            String compensatorUrl = String.format("%s/%s", coordinatorUrl,
//                    NarayanaLRAClient.encodeURL(lraId, "Invalid parent LRA id"));
//            Response response;
//
//            if (lraService.hasTransaction(parentLRAUrl))
//                response = joinLRAViaBody(parentLRAUrl.toExternalForm(), lra.getTimeout(), null, compensatorUrl);
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
