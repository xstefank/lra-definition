package org.xstefank.lra.model;

import lombok.ToString;

@ToString
public class ActionResult {

    private Result result;
    private String message;
    private Throwable cause;

    private ActionResult(Result result, String message, Throwable cause) {
        this.result = result;
        this.message = message;
        this.cause = cause;
    }

    public static ActionResult success() {
        return new ActionResult(Result.SUCCESS, null, null);
    }

    public static ActionResult success(String message) {
        return new ActionResult(Result.SUCCESS, message, null);
    }

    public static ActionResult failure() {
        return new ActionResult(Result.FAILURE, null, null);
    }

    public static ActionResult failure(String message) {
        return new ActionResult(Result.FAILURE, message, null);
    }

    public static ActionResult failure(Throwable cause) {
        return new ActionResult(Result.FAILURE, null, cause);
    }

    public static ActionResult failure(String message, Throwable cause) {
        return new ActionResult(Result.FAILURE, message, cause);
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

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
