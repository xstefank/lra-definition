package org.xstefank.lra.execution;

import org.xstefank.lra.definition.LRADefinition;

import java.net.URL;

public interface LRAExecutor {

    /**
     * Executes the LRA according to the definition
     *
     * @param lraId the URL id of the LRA to be executed
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    void executeLRA(URL lraId, LRADefinition lraDefinition);

}
