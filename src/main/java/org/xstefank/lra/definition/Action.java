package org.xstefank.lra.definition;

import org.xstefank.lra.model.LRAResult;

@FunctionalInterface
public interface Action {

    /**
     * The invocation of the work that is to be performed
     * by the action
     *
     * @param data optional LRA data
     * @return the result of the action invocation
     */
    LRAResult invoke(Object data);

}
