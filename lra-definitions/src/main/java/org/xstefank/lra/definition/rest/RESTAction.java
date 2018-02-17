package org.xstefank.lra.definition.rest;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.model.ActionResult;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class RESTAction implements Action {

    private Logger log = Logger.getLogger(RESTAction.class);

    private final URL target;

    private RESTAction(URL target) {
        this.target = target;
    }

    public static RESTAction post(URL target) {
        return new RESTAction(target);
    }

    @Override
    public ActionResult invoke(String lraId, Object data) {
        log.infof("executing action - %s", this);

        Client client = ClientBuilder.newClient();
        URI build = null;
        try {
            build = UriBuilder
                    .fromUri(target.toURI())
                    .build();
        } catch (URISyntaxException ex) {
            return ActionResult.failure(ex);
        }

        log.info("action request url - " + build);
        WebTarget target = client.target(build);

        Response response = target.request().header("Long-Running-Action", lraId).post(Entity.json(data));

        ActionResult result = response.getStatus() == Response.Status.OK.getStatusCode() ?
                ActionResult.success() : ActionResult.failure();

        response.close();

        return result;
    }
}
