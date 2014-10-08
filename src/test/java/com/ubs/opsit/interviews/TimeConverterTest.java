package com.ubs.opsit.interviews;

import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import com.ubs.opsit.interviews.service.StringTimeConverter;
import com.ubs.opsit.interviews.service.TimeConverter;
import org.junit.Assert;
import org.junit.Test;

public class TimeConverterTest {

    static final TimeConverter TIME_CONVERTER = new StringTimeConverter();

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
    public void secondLightShouldBlinkOnOffEveryTwoSeconds() {

//        Assert.assertEquals("YELLOW", berlinClock.getSeconds(0));
//        Assert.assertEquals("O", berlinClock.getSeconds(1));
//        Assert.assertEquals("O", berlinClock.getSeconds(59));
    }

    @Test
    public void convertTimeInput() throws Exception {
//        Assert.assertEquals("YELLOW\n" +
//                "OOOO\n" +
//                "OOOO\n" +
//                "OOOOOOOOOOO\n" +
//                "OOOO", TIME_CONVERTER.convertTime("15:23:50"));
    }
}

