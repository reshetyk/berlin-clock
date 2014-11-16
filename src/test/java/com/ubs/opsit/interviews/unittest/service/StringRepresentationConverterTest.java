package com.ubs.opsit.interviews.unittest.service;

import com.ubs.opsit.interviews.service.StringRepresentationConverter;
import com.ubs.opsit.interviews.service.TimeConverter;
import com.ubs.opsit.interviews.service.exception.TimeParserException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringRepresentationConverterTest {

    private static final String SEPARATOR = StringRepresentationConverter.SEPARATOR;
    TimeConverter timeConverter;

    @Before
    public void setUp() throws Exception {
        timeConverter = StringRepresentationConverter.getDefaultInstance();
    }

    //TODO: split asserts into separate methods
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

        Assert.assertEquals(
                "Y" + SEPARATOR + "RRRR" + SEPARATOR + "RRRR" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                timeConverter.convertTime("24:00:00")
        );
    }

    //bad cases below

    @Test(expected = TimeParserException.class)
    public void convertTimeInputParameterNull() throws Exception {
        timeConverter.convertTime(null);
    }

    @Test(expected = TimeParserException.class)
    public void convertTimeInputParameterEmptyString() throws Exception {
        timeConverter.convertTime("");
    }

    @Test(expected = TimeParserException.class)
    public void convertTimeInputInvalidParameter() throws Exception {
        timeConverter.convertTime("0dds!a-");
    }
}

