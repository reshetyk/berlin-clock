package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.builder.BerlinClockDateBuilderImpl;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializerImpl;
import com.ubs.opsit.interviews.service.TimeConverter;
import com.ubs.opsit.interviews.service.TimeConverterImpl;
import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import com.ubs.opsit.interviews.utils.Utils;
import org.junit.Test;

public class TimeConverterTest {

    static final TimeConverter TIME_CONVERTER = new TimeConverterImpl(
            Utils.getConfigProperty("inputTimeFormat"),
            new BerlinClockDateBuilderImpl(),
            new BerlinClockSerializerImpl()
    );

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

//    @Test
//    public void secondLightShouldBlinkOnOffEveryTwoSeconds() {
//        TimeConverter timeConverterMock = mock (TimeConverter.class);
//        when(timeConverterMock.convertTime("16:17:10")).thenReturn("O\nRROO\nRRRO\nYYROOOOOOOO\nYYOO");
//        when(timeConverterMock.convertTime("00:00:00")).then("O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO");
//
//        Assert.assertEquals("YELLOW", berlinClock.getSeconds(0));
//        Assert.assertEquals("O", berlinClock.getSeconds(1));
//        Assert.assertEquals("O", berlinClock.getSeconds(59));
//    }

//    @Test
//    public void convertTimeInput() throws Exception {
//        Assert.assertEquals("YELLOW\n" +
//                "OOOO\n" +
//                "OOOO\n" +
//                "OOOOOOOOOOO\n" +
//                "OOOO", TIME_CONVERTER.convertTime("15:23:50"));
//    }
}

