package org.xstefank.lra.definition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = LRADefinitionImpl.class)
@JsonDeserialize(as = LRADefinitionImpl.class)
public interface LRADefinition {

    /**
     * Provides a simple human readable name of the LRA
     */
    String getName();

    /**
     * The list of actions that the LRA consists of
     */
    List<Action> getActions();

    /**
     * Optional data associated with the LRA
     */
    Object getData();

    /**
     * The list of nested LRA definitions
     */
    List<LRADefinition> getNestedLRAs();

    /**
     * Parent LRA identification if the parent is available
     */
    String getParentLRA();

    /**
     * Unique identifier of the intiating client
     */
    String getClientId();

    /**
     * The time out in milliseconds after which the LRA is cancelled
     */
    long getTimelimit();

}
