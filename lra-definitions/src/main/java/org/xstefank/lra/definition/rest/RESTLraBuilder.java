package org.xstefank.lra.definition.rest;

import org.xstefank.lra.definition.LRABuilder;

public class RESTLraBuilder extends LRABuilder<RESTLraBuilder, RESTLra> {

    private String callbackURL;

    public static RESTLraBuilder lra() {
        return new RESTLraBuilder();
    }

    public RESTLraBuilder callback(String callbackURL) {
        this.callbackURL = callbackURL;
        return this;
    }

    @Override
    public RESTLra build() {
        return new RESTLraImpl(name, actions, data, nested, parentLRA, clientId, timeout, callbackURL);
    }
}
