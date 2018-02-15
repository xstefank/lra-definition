package org.xstefank.lra.definition.rest;

import org.xstefank.lra.model.LRAResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public interface LRACallback {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void complete(@QueryParam(RESTLra.LRA_RESULT_PARAM_NAME) LRAResult lraResult);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void compensate(@QueryParam(RESTLra.LRA_RESULT_PARAM_NAME) LRAResult lraResult);

}
