package org.xstefank.lra.execution;

import org.xstefank.lra.definition.LRADefinition;

public interface LRAExecutor {

    /**
     * Executes the LRA according to the definition
     */
    void executeLRA(LRADefinition lraDefinition);

}
