package com.ubs.opsit.interviews.service.exception;

/**
 * Created by Alexey on 07.10.2014.
 */
public class TimeConverterException extends RuntimeException {
    public TimeConverterException (String msg) {
        super(msg);
    }
    public TimeConverterException (Exception ex) {
        super(ex);
    }



}
