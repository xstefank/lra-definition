package org.xstefank.lra.execution;

import org.xstefank.lra.definition.LRADefinition;


public interface LRAExecutor {

    /**
     * Processed the LRA in order defined by the action list
     *
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    void executeLRA(LRADefinition lraDefinition);

}
