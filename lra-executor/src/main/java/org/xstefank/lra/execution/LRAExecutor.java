package org.xstefank.lra.execution;

import org.xstefank.lra.definition.LRADefinition;
import org.xstefank.lra.execution.model.LRAResult;

import java.net.URL;
import java.util.concurrent.Future;


public interface LRAExecutor {

    /**
     * Starts and executes LRA synchronously
     *
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    LRAResult executeLRA(LRADefinition lraDefinition);

    /**
     * Starts and executes LRA asynchronously
     *
     * @param lraDefinition the definition of the LRA according to which it is to be executed
     */
    Future<LRAResult> executeLRAAsync(LRADefinition lraDefinition);

    /**
     * Starts new LRA
     *
     * @param lraDefinition the definition of LRA
     * @return the LRA identification
     */
    URL startLRA(LRADefinition lraDefinition);
}
