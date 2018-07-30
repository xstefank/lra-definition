package org.xstefank.lra.definition;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.xstefank.lra.model.ActionResult;
import org.xstefank.lra.model.LRAData;

@FunctionalInterface
public interface Action {

    /**
     * The invocation of the work that is to be performed
     * by the action
     *
     * @param lraData LRA data associated with parent LRA
     * @return the result of the action invocation
     */
    ActionResult invoke(LRAData lraData);
    
    @JsonIgnore
    default String getResultName() {
        return null;
    }
    
}
