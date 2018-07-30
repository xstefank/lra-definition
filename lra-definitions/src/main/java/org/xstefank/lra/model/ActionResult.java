package org.xstefank.lra.model;

import lombok.ToString;

@ToString
public class ActionResult {

    private ActionOutcome outcome;
    private Object result;
    private Throwable cause;

    private ActionResult(ActionOutcome outcome, Object result, Throwable cause) {
        this.outcome = outcome;
        this.result = result;
        this.cause = cause;
    }

    public static ActionResult success() {
        return new ActionResult(ActionOutcome.SUCCESS, null, null);
    }

    public static ActionResult success(Object result) {
        return new ActionResult(ActionOutcome.SUCCESS, result, null);
    }

    public static ActionResult failure() {
        return new ActionResult(ActionOutcome.FAILURE, null, null);
    }

    public static ActionResult failure(Object result) {
        return new ActionResult(ActionOutcome.FAILURE, result, null);
    }

    public static ActionResult failure(Throwable cause) {
        return new ActionResult(ActionOutcome.FAILURE, null, cause);
    }

    public static ActionResult failure(Object result, Throwable cause) {
        return new ActionResult(ActionOutcome.FAILURE, result, cause);
    }

    public boolean isSuccess() {
        return outcome.equals(ActionOutcome.SUCCESS);
    }

    public boolean isFailure() {
        return outcome.equals(ActionOutcome.FAILURE);
    }

    public ActionOutcome getOutcome() {
        return outcome;
    }

    public Object getResult() {
        return result;
    }

    public Throwable getCause() {
        return cause;
    }
}
