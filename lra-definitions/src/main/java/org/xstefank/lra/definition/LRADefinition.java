package org.xstefank.lra.definition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = LRADefinitionImpl.class)
@JsonDeserialize(as = LRADefinitionImpl.class)
public interface LRADefinition {

    /**
     * Provides a simple identifaction of the LRA
     */
    String getId();

    /**
     * The list of actions that the LRA consists of
     */
    List<Action> getActions();

    /**
     * Optional data associated with the LRA
     */
    Object getData();

}
