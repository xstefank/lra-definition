package org.xstefank.lra.definition;

import org.xstefank.lra.model.ActionResult;

public class TestUtil {

    public static Action dummyAction() {
        return d -> ActionResult.success();
    }
}
