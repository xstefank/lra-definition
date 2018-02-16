package org.xstefank.lra.definition.rest;

import org.xstefank.lra.definition.Action;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.definition.LRADefinitionImpl;

import java.util.List;

public class RESTLraImpl extends LRADefinitionImpl implements RESTLra{

    private String callbackURL;
    private boolean subscribeCallerAsParticipant;

    public RESTLraImpl(String name, List<Action> actions, Object data, List<LRADefinition> nested,
                       String parentLRA, String clientId, long timeout, String callbackURL, boolean subscribeCallerAsParticipant) {
        super(name, actions, data, nested, parentLRA, clientId, timeout);

        if (callbackURL == null) {
            throw new IllegalArgumentException("Callback address cannot be null");
        }

        this.callbackURL = callbackURL;
        this.subscribeCallerAsParticipant = subscribeCallerAsParticipant;
    }

    @Override
    public String getCallbackURL() {
        return callbackURL;
    }

    @Override
    public boolean getSubscribeCallerAsParticipant() {
        return subscribeCallerAsParticipant;
    }
}
