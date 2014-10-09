package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.builder.BerlinClockDateBuilder;
import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializer;
import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class TimeConverterImpl implements TimeConverter {
    private final String inputTimeFormat;
    private final BerlinClockDateBuilder berlinClockDateBuilder;
    private final BerlinClockSerializer berlinClockSerializer;

    public TimeConverterImpl(String inputTimeFormat, BerlinClockDateBuilder berlinClockDateBuilder, BerlinClockSerializer berlinClockSerializer) {
        this.inputTimeFormat = inputTimeFormat;
        this.berlinClockDateBuilder = berlinClockDateBuilder;
        this.berlinClockSerializer = berlinClockSerializer;
    }

    @Override
    public String convertTime(String sInputTime) {
        final Date date = parseTime(sInputTime, inputTimeFormat);
        final BerlinClock berlinClock = berlinClockDateBuilder.buildByDate(date);

        return berlinClockSerializer.serializeAsString(berlinClock);
    }

    public static Date parseTime(String sTime, String format) throws TimeConverterException {
        if (sTime == null || sTime.isEmpty()) {
            throw new TimeConverterException("invalid parameter '" + sTime + "'");
        }
        DateTimeFormatter dateTimeFormatter;
        DateTime dateTime;
        try {
            dateTimeFormatter = DateTimeFormat.forPattern(format);
            dateTime = dateTimeFormatter.parseDateTime(sTime);
        } catch (IllegalArgumentException ex) {
            throw new TimeConverterException(ex);
        }

        return dateTime.toDate();
    }


}
