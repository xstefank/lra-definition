package org.xstefank.lra.definition;

public interface Action {

    /**
     * Simple identifaction of the action
     */
    String getName();

    /**
     * The invocation of the work the action performs
     */
    void invoke();

}
