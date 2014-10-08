package com.ubs.opsit.interviews.utils;

import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;

public class DateToBerlinClockConverterTest {

    @Test
    public void testConvert() throws Exception {

    }

    @Test
    public void testSetUpSecondLight() throws Exception {
        final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("HH:mm:ss");
        final BerlinClock berlinClock = new BerlinClock();

        DateToBerlinClockConverterImpl instance =
                new DateToBerlinClockConverterImpl(dtFormatter.parseDateTime("12:04:10").toDate(), berlinClock);
        instance.setUpSecondLight();
        Assert.assertEquals(BerlinClockLight.State.YELLOW, berlinClock.getSecondLight().getState());

        DateToBerlinClockConverterImpl instance2=
                new DateToBerlinClockConverterImpl(dtFormatter.parseDateTime("12:04:11").toDate(), berlinClock);
        instance2.setUpSecondLight();
        Assert.assertEquals(BerlinClockLight.State.OFF, berlinClock.getSecondLight().getState());

    }

    @Test
    public void testSetUpTopHoursLights() throws Exception {
        final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("HH:mm:ss");
        final BerlinClock berlinClock = new BerlinClock();

        DateToBerlinClockConverterImpl instance =
                new DateToBerlinClockConverterImpl(dtFormatter.parseDateTime("12:04:10").toDate(), berlinClock);
        instance.setUpTopHoursLights();

        BerlinClockLight[] berlinClockLightsExpected = new BerlinClockLight[4];
        berlinClockLightsExpected[0] = new BerlinClockLight(BerlinClockLight.State.RED);
        berlinClockLightsExpected[1] = new BerlinClockLight(BerlinClockLight.State.RED);
        berlinClockLightsExpected[2] = new BerlinClockLight(BerlinClockLight.State.OFF);
        berlinClockLightsExpected[3] = new BerlinClockLight(BerlinClockLight.State.OFF);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(berlinClockLightsExpected, berlinClock.getTopHoursLights()));

    }
}

class DateToBerlinClockConverterImpl extends DateToBerlinClockConverter {
    DateToBerlinClockConverterImpl(Date date, BerlinClock berlinClock) {
        super(date, berlinClock);
    }
}