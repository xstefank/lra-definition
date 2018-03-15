package org.xstefank.lra.model;

import lombok.ToString;

@ToString
public class ActionResult {

    private Result result;
    private String cause;

    public ActionResult(Result result) {
        this.result = result;
    }

    private ActionResult(Result result, String cause) {
        this.result = result;
        this.cause = cause;
    }

    public static ActionResult success() {
        return new ActionResult(Result.SUCCESS);
    }

    public static ActionResult failure() {
        return new ActionResult(Result.FAILURE);
    }

    public static ActionResult failure(String cause) {
        return new ActionResult(Result.FAILURE, cause);
    }

    public static ActionResult failure(Exception ex) {
        return new ActionResult(Result.FAILURE, ex.getMessage());
    }

    public boolean isSuccess() {
        return result.equals(Result.SUCCESS);
    }

    public boolean isFailure() {
        return result.equals(Result.FAILURE);
    }

    public Result getResult() {
        return result;
    }

}
