package model.Exceptions;

public class ErrorModel extends Exception {
    private Exception exception;
    private String message;

    public ErrorModel(Exception ex, String message) {
        this.exception = ex;
        this.message = message;
    }

    public Exception getException() {
        return this.exception;
    }

    public String getMessage() {
        return this.message;
    }
}
