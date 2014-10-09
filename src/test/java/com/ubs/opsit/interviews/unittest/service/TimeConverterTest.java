package com.ubs.opsit.interviews.unittest.service;

import com.ubs.opsit.interviews.service.TimeConverter;
import com.ubs.opsit.interviews.service.TimeConverterImpl;
import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import org.junit.Assert;
import org.junit.Test;

public class TimeConverterTest {

    static final TimeConverter TIME_CONVERTER = TimeConverterImpl.getDefaultInstance();

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterNull() throws Exception {
        TIME_CONVERTER.convertTime(null);
    }

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterEmptyString() throws Exception {
        TIME_CONVERTER.convertTime("");
    }

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterInvalidString() throws Exception {
        TIME_CONVERTER.convertTime("0dds");
    }

    @Test
    public void convertTime() throws Exception {

        Assert.assertEquals(
                "Y\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO",
                TIME_CONVERTER.convertTime("00:00:00")
        );

        Assert.assertEquals(
                "O\n" + "RRRO\n" + "OOOO\n" + "YYOOOOOOOOO\n" + "YYYY",
                TIME_CONVERTER.convertTime("15:14:05")
        );

        Assert.assertEquals(
                "O\n" + "RRRR\n" + "RRRO\n" + "YYRYYRYYRYY\n" + "YYYY",
                TIME_CONVERTER.convertTime("23:59:59")
        );

        Assert.assertEquals(
                "O\n" + "RRRR\n" + "RROO\n" + "YYOOOOOOOOO\n" + "YOOO",
                TIME_CONVERTER.convertTime("22:11:11")
        );
    }
}

