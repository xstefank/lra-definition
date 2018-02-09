package org.xstefank.lra.rest;

import org.jboss.logging.Logger;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.execution.LRAExecutor;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/lra")
public class LRAEndpoint {

    private static final Logger log = Logger.getLogger(LRAEndpoint.class);

    @EJB
    private LRAExecutor lraExecutor;

    @Context
    private UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String lraRequest(LRADefinition lraDefinition) {
        log.info("received lra - " + lraDefinition);
        lraExecutor.executeLRA(lraDefinition);
        return "processing LRA - " + lraDefinition;
    }

}
