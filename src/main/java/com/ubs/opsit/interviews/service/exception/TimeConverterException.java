package com.ubs.opsit.interviews.service.exception;

public class TimeConverterException extends RuntimeException {
    public TimeConverterException (String msg) {
        super(msg);
    }
    public TimeConverterException (Exception ex) {
        super(ex);
    }



}
