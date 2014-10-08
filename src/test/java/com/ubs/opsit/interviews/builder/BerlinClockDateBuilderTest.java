package com.ubs.opsit.interviews.builder;

import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class BerlinClockDateBuilderTest {

    @Test
    public void testConvert() throws Exception {

    }

    @Test
    public void testSetUpSecondLight() throws Exception {
        final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        BerlinClockDateBuilderImpl builder = new BerlinClockDateBuilderImpl();
        final BerlinClock berlinClockSecondYellow = builder.buildByDate(dtFormatter.parseDateTime("12:04:10").toDate());
        final BerlinClock berlinClockSecondOff = builder.buildByDate(dtFormatter.parseDateTime("12:04:11").toDate());

        Assert.assertEquals(BerlinClockLight.State.YELLOW, berlinClockSecondYellow.getSecondLight().getState());
        Assert.assertEquals(BerlinClockLight.State.OFF, berlinClockSecondOff.getSecondLight().getState());

    }

    @Test
    public void testSetUpTopHoursLights() throws Exception {
/*
        final DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("HH:mm:ss");
        final BerlinClock berlinClock = new BerlinClock();

        BerlinClockDateBuilderImpl instance =
                new BerlinClockDateBuilderImpl(dtFormatter.parseDateTime("12:04:10").toDate(), berlinClock);
        instance.setUpTopHoursLights();

        BerlinClockLight[] berlinClockLightsExpected = new BerlinClockLight[4];
        berlinClockLightsExpected[0] = new BerlinClockLight(BerlinClockLight.State.RED);
        berlinClockLightsExpected[1] = new BerlinClockLight(BerlinClockLight.State.RED);
        berlinClockLightsExpected[2] = new BerlinClockLight(BerlinClockLight.State.OFF);
        berlinClockLightsExpected[3] = new BerlinClockLight(BerlinClockLight.State.OFF);
        //TODO:
        Assert.assertTrue(EqualsBuilder.reflectionEquals(berlinClockLightsExpected, berlinClock.getTopHoursLights()));
*/

    }
}
