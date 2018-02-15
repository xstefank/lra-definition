package org.xstefank.lra.definition.rest;

import org.xstefank.lra.definition.LRABuilder;

public class RESTLraBuilder extends LRABuilder {

    private String callbackURL;


    public RESTLraBuilder callback(String callbackURL) {
        this.callbackURL = callbackURL;
        return this;
    }
}
