package org.xstefank.lra.definition;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(as = LRADefinitionImpl.class)
@JsonDeserialize(as = LRADefinitionImpl.class)
public interface LRADefinition<T extends Action> {

    /**
     * Provides a simple human readable name of the LRA
     */
    String getName();

    /**
     * The list of actions that the LRA consists of
     */
    List<T> getActions();

    /**
     * Optional data associated with the LRA
     */
    Object getData();

    /**
     * The list of nested LRA definitions
     */
    List<LRADefinition<T>> getNestedLRAs();

    String getParentLRA();

    String getClientId();

    long getTimeout();

}
