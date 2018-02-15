package org.xstefank.lra.definition.rest;

import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.definition.LRADefinitionImpl;

import java.util.List;

public class RESTLraImpl extends LRADefinitionImpl implements RESTLra{

    private String callbackURL;

    public RESTLraImpl(String name, List<Action> actions, Object data, List<LRADefinition> nested, String callbackURL) {
        super(name, actions, data, nested);

        if (callbackURL == null) {
            throw new IllegalArgumentException("Callback address cannot be null");
        }

        this.callbackURL = callbackURL;
    }

    @Override
    public String getCallbackURL() {
        return callbackURL;
    }
}
