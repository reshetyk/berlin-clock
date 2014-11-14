package com.ubs.opsit.interviews.unittest.service;

import com.ubs.opsit.interviews.service.StringRepresentationConverter;
import com.ubs.opsit.interviews.service.TimeConverter;
import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TimeConverterTest {

    private static final String SEPARATOR = "\n\r";
    TimeConverter timeConverter;

    @Before
    public void setUp() throws Exception {
        timeConverter = StringRepresentationConverter.getDefaultInstance();
    }

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterNull() throws Exception {
        timeConverter.convertTime(null);
    }

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterEmptyString() throws Exception {
        timeConverter.convertTime("");
    }

    @Test(expected = TimeConverterException.class)
    public void convertTimeInputParameterInvalidString() throws Exception {
        timeConverter.convertTime("0dds");
    }

    @Test
    public void convertTime() throws Exception {

        Assert.assertEquals(
                "Y" + SEPARATOR + "OOOO" + SEPARATOR + "OOOO" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                timeConverter.convertTime("00:00:00")
        );

        Assert.assertEquals(
                "O" + SEPARATOR + "RRRO" + SEPARATOR + "OOOO" + SEPARATOR + "YYOOOOOOOOO" + SEPARATOR + "YYYY",
                timeConverter.convertTime("15:14:05")
        );

        Assert.assertEquals(
                "O" + SEPARATOR + "RRRR" + SEPARATOR + "RRRO" + SEPARATOR + "YYRYYRYYRYY" + SEPARATOR + "YYYY",
                timeConverter.convertTime("23:59:59")
        );

        Assert.assertEquals(
                "O" + SEPARATOR + "RRRR" + SEPARATOR + "RROO" + SEPARATOR + "YYOOOOOOOOO" + SEPARATOR + "YOOO",
                timeConverter.convertTime("22:11:11")
        );
    }
}

