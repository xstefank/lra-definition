package org.xstefank.lra.definition;

import org.xstefank.lra.model.ActionResult;

@FunctionalInterface
public interface Action {

    /**
     * The invocation of the work that is to be performed
     * by the action
     *
     * @param lraId the id of the enclosing LRA
     * @param data optional LRA data
     * @return the result of the action invocation
     */
    ActionResult invoke(String lraId, Object data);

}
