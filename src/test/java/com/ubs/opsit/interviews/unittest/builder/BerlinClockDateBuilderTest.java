package com.ubs.opsit.interviews.unittest.builder;

import com.google.gson.Gson;
import com.ubs.opsit.interviews.builder.BerlinClockDateBuilderImpl;
import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BerlinClockDateBuilderTest {

    final static DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("HH:mm:ss");
    final static BerlinClockDateBuilderImpl builder = new BerlinClockDateBuilderImpl();

    /**
     * Check that on the top of the clock there is a yellow lamp that blinks on/off every two seconds
     *
     * @throws Exception
     */
    @Test
    public void secondLightOnOffEveryTwoSeconds() throws Exception {
        assertEquals(YELLOW, buildBerlinClock("00:00:00").getSecondLight().getState());
        assertEquals(OFF, buildBerlinClock("00:00:01").getSecondLight().getState());
        assertEquals(YELLOW, buildBerlinClock("00:00:02").getSecondLight().getState());
        assertEquals(OFF, buildBerlinClock("00:00:03").getSecondLight().getState());
    }

    /**
     * Check that top hours have 4 lamps
     *
     * @throws Exception
     */
    @Test
    public void topHoursLightsHaveFourLights() throws Exception {
        assertEquals(4, new BerlinClock().getTopHoursLights().size());
    }

    /**
     * Check that the lamps are lit red when enabled
     *
     * @throws Exception
     */
    @Test
    public void allEnabledTopHourLightsAreRed() throws Exception {
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(buildBerlinClock("20:00:00").getTopHoursLights())
        );
    }

    /**
     * Check that every top hour lamp represents 5 hours
     *
     * @throws Exception
     */
    @Test
    public void topHourLightsRepresentFiveHours() throws Exception {
        assertEquals(
                toJson(makeLightsByEnum(RED, OFF, OFF, OFF)),
                toJson(buildBerlinClock("05:00:00").getTopHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, OFF, OFF)),
                toJson(buildBerlinClock("12:00:00").getTopHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, OFF)),
                toJson(buildBerlinClock("16:00:00").getTopHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(buildBerlinClock("23:00:00").getTopHoursLights())
        );
    }

    /**
     * Check that in the lower row of red lamps every lamp represents 1 hour.
     * Example: so if two lamps of the first row and three of the second row are
     * switched on that indicates 5+5+3=13h or 1 pm.
     *
     * @throws Exception
     */
    @Test
    public void bottomHourLightsRepresentOneHour() throws Exception {
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, OFF)),
                toJson(buildBerlinClock("13:00:00").getBottomHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(buildBerlinClock("14:00:00").getBottomHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, OFF, OFF)),
                toJson(buildBerlinClock("12:00:00").getBottomHoursLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(OFF, OFF, OFF, OFF)),
                toJson(buildBerlinClock("10:00:00").getBottomHoursLights())
        );
    }

    /**
     * Check that top minutes have 11 lamps
     *
     * @throws Exception
     */
    @Test
    public void topMinutesHaveElevenLights() throws Exception {
        assertEquals(11, new BerlinClock().getTopMinutesLights().size());
    }

    /**
     * Check that in top minutes every lamp represents 5 minutes.
     * The 3rd, 6th and 9th lamp are red and indicate the first
     * quarter, half and last quarter of an hour. The other lamps are yellow
     *
     * @throws Exception
     */
    @Test
    public void topMinuteLightsRepresentFiveMinute() throws Exception {
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, OFF, OFF)),
                toJson(buildBerlinClock("00:45:00").getTopMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW)),
                toJson(buildBerlinClock("00:56:00").getTopMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)),
                toJson(buildBerlinClock("00:11:00").getTopMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF)),
                toJson(buildBerlinClock("00:20:00").getTopMinutesLights())
        );
    }

    /**
     * Check that bottom minutes have 4 lamps
     *
     * @throws Exception
     */
    @Test
    public void bottomMinutesHaveFourLights() throws Exception {
        assertEquals(4, new BerlinClock().getBottomMinutesLights().size());
    }

    /**
     * Check that bottom minutes represents 1 minute
     *
     * @throws Exception
     */
    @Test
    public void bottomMinuteLightsRepresentsOneMinute() throws Exception {
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, YELLOW, YELLOW)),
                toJson(buildBerlinClock("00:04:00").getBottomMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, YELLOW, OFF)),
                toJson(buildBerlinClock("00:03:00").getBottomMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, OFF, OFF)),
                toJson(buildBerlinClock("00:02:00").getBottomMinutesLights())
        );
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, OFF, OFF, OFF)),
                toJson(buildBerlinClock("00:06:00").getBottomMinutesLights())
        );
    }

    @Test
    @Ignore
    public void allLightsInBerlinClockSetUpCorrect() throws Exception {
        final BerlinClock actual = buildBerlinClock("11:02:12");
        final BerlinClock expected = new BerlinClock();

        when(expected.getSecondLight()).thenReturn(new BerlinClockLight(OFF));
        when(expected.getTopHoursLights()).thenReturn(makeLightsByEnum(RED, RED, OFF, OFF));
        when(expected.getBottomHoursLights()).thenReturn(makeLightsByEnum(RED, OFF, OFF, OFF));
        when(expected.getTopMinutesLights()).thenReturn(makeLightsByEnum(OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF));
        when(expected.getBottomMinutesLights()).thenReturn(makeLightsByEnum(RED, RED, OFF, OFF));

        //TODO: figure out how to serialize spy object to json or how to equals these objects correctly
        //assertEquals(toJson(expected), toJson(actual));
    }

    //TODO: maybe extract functions below to TestUtils or TestHelper class
    private static List<BerlinClockLight> makeLightsByEnum(BerlinClockLight.State... states) {
        final List<BerlinClockLight> result = new ArrayList<BerlinClockLight>(states.length);
        for (int i = 0; i < states.length; i++) {
            result.add(new BerlinClockLight(states[i]));
        }
        return result;
    }

    private static BerlinClock buildBerlinClock(String sDate) {
        return builder.buildByDate(dtFormatter.parseDateTime(sDate).toDate());
    }

    private static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
