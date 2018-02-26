package org.xstefank.lra.definition.rest;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.definition.LRADefinitionImpl;

import java.util.List;

@ToString
@NoArgsConstructor
public class RESTLraImpl extends LRADefinitionImpl<RESTAction> implements RESTLra {

    private String callbackURL;
    private boolean subscribeCallerAsParticipant;

    public RESTLraImpl(String name, List<RESTAction> actions, Object data, List<LRADefinition> nested,
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
