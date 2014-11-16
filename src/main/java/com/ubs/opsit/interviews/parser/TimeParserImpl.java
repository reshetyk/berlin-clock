package com.ubs.opsit.interviews.parser;

import com.ubs.opsit.interviews.domain.BerlinTime;
import com.ubs.opsit.interviews.service.exception.TimeParserException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeParserImpl implements TimeParser {

    @Override
    public BerlinTime parseAsBerlinTime(String sTime, String format) {
        if (sTime == null || sTime.isEmpty()) {
            throw new TimeParserException("invalid parameter '" + sTime + "'");
        }
        if ("ZZ:mm:ss".equals(format)) {
            return parseAndValidateBySpecialFormat(sTime, format);
        } else {
            return parseAndValidateByStandardFormat(sTime, format);
        }

    }

    protected BerlinTime parseAndValidateBySpecialFormat(String sTime, String format) {
        final String errMsg = "Can't parse time '" + sTime + "' by format '" + format + "'";
        int hours, mins , secs;
        try {
            final String[] splitTime = sTime.split(":");
            hours = Integer.parseInt(splitTime[0]);
            mins = Integer.parseInt(splitTime[1]);
            secs = Integer.parseInt(splitTime[2]);
            if (hours > 24 || hours < 0 || mins > 60 || mins < 0 || secs > 60 || secs < 0) {
                throw new TimeParserException(errMsg + "; incorrect ranges");
            }
        } catch (Exception ex) {
            throw new TimeParserException(errMsg, ex);
        }
        return new BerlinTime(secs, mins, hours);
    }

    protected BerlinTime parseAndValidateByStandardFormat(String sTime, String format) {

        DateTimeFormatter dateTimeFormatter;
        DateTime dateTime;
        try {
            dateTimeFormatter = DateTimeFormat.forPattern(format);
            dateTime = dateTimeFormatter.parseDateTime(sTime);
        } catch (Exception ex) {
            throw new TimeParserException(ex);
        }

        return new BerlinTime(dateTime.getSecondOfMinute(), dateTime.getMinuteOfHour(), dateTime.getHourOfDay());
    }
}