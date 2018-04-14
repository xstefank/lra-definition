package org.xstefank.lra.definition.rest;

import java.net.URL;

public class RESTActionBuilder {

    private URL target;
    private URL callbackUrl;

    public RESTActionBuilder(URL target) {
        this.target = target;
    }

    public RESTActionBuilder callbackUrl(URL callbackUrl) {
        this.callbackUrl = callbackUrl;
        return this;
    }

    public RESTAction build() {
        return new RESTAction(target, callbackUrl);
    }
}
