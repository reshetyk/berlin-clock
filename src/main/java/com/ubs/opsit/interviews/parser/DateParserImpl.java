package com.ubs.opsit.interviews.parser;

import com.ubs.opsit.interviews.domain.BerlinTime;
import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateParserImpl implements DateParser {

    @Override
    public BerlinTime parseAsBerlinTime(String sDate, String formatDate) {
        final DateTime dateTime = parseTimeByFormat(sDate, formatDate);
        return new BerlinTime(dateTime.getSecondOfMinute(), dateTime.getMinuteOfHour(), dateTime.getHourOfDay());
    }

    protected static DateTime parseTimeByFormat(String sTime, String format) throws TimeConverterException {
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

        return dateTime;
    }
}
