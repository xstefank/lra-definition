package org.xstefank.lra.definition.rest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jboss.logging.Logger;
import org.xstefank.lra.definition.Action;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

//TODO add data for request
@ToString(callSuper = true)
@NoArgsConstructor
public class RESTAction implements Action {

    @JsonIgnore
    private Logger log = Logger.getLogger(RESTAction.class);

    @JsonProperty
    private URL target;

    private RESTAction(URL target) {
        this.target = target;
    }

    public static RESTAction post(URL target) {
        return new RESTAction(target);
    }

    @Override
    public ActionResult invoke(LRAData lraData) {
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

        Response response = target.request()
                .header("Long-Running-Action", lraData.getLraId())
                .post(Entity.json(lraData.getData()));

        ActionResult result = response.getStatus() == Response.Status.OK.getStatusCode() ?
                ActionResult.success() : ActionResult.failure();

        response.close();

        return result;
    }

    public URL getTarget() {
        return target;
    }
}
