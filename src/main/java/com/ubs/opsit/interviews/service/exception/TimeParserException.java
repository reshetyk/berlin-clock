package com.ubs.opsit.interviews.service.exception;

public class TimeParserException extends RuntimeException {
    public TimeParserException(String msg) {
        super(msg);
    }

    public TimeParserException(Exception ex) {
        super(ex);
    }

    public TimeParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
