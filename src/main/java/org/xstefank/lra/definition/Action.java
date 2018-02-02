package org.xstefank.lra.definition;

@FunctionalInterface
public interface Action {

    /**
     * The invocation of the work that is to be performed
     * by the action
     *
     * @param data optional LRA data
     */
    void invoke(Object data);

}
