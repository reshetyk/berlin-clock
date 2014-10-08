package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import com.ubs.opsit.interviews.utils.BerlinClockToStringConverter;
import com.ubs.opsit.interviews.utils.DateToBerlinClockConverter;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Created by Alexey on 07.10.2014.
 */
public class StringTimeConverter implements TimeConverter {
    @Override
    public String convertTime(String sInputTime) {
        DateToBerlinClockConverter dateToBerlinClockConverter
                = new DateToBerlinClockConverter(parseTime(sInputTime));

        BerlinClockToStringConverter berlinClockToStringConverter
                = new BerlinClockToStringConverter(dateToBerlinClockConverter.convert());

        return berlinClockToStringConverter.convertToString();

    }

    public static Date parseTime(String sTime) throws TimeConverterException {
        if (sTime == null || sTime.isEmpty()) {
            throw new TimeConverterException("invalid parameter '" + sTime + "'");
        }
        DateTimeFormatter dateTimeFormatter;
        DateTime dateTime;
        try {
            dateTimeFormatter = DateTimeFormat.forPattern("HH:mm:ss");//TODO: the format should be obtained from config file
            dateTime = dateTimeFormatter.parseDateTime(sTime);
        } catch (IllegalArgumentException ex) {
            throw new TimeConverterException(ex);
        }

        return dateTime.toDate();
    }


}
