package com.ubs.opsit.interviews.unittest.driver;

import com.google.gson.Gson;
import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import com.ubs.opsit.interviews.driver.BerlinClockDriverImpl;
import com.ubs.opsit.interviews.parser.TimeParser;
import com.ubs.opsit.interviews.parser.TimeParserImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State.*;
import static org.junit.Assert.assertEquals;

public class BerlinClockDriverTest {

    final static String FORMAT_INPUT_TIME = "HH:mm:ss";

    BerlinClockDriverImpl driver;
    TimeParser timeParser;
    BerlinClockDevice clockDevice;

    @Before
    public void setUp() throws Exception {
        clockDevice = new BerlinClockDevice();
        driver = new BerlinClockDriverImpl(clockDevice);
        timeParser = new TimeParserImpl();
    }

    /**
     * Check that on the top of the clock there is a yellow lamp that blinks on/off every two seconds
     *
     * @throws Exception
     */
    @Test
    public void secondLightOnOffEveryTwoSeconds() throws Exception {
        setBerlinClockTime("00:00:00");
        assertEquals(YELLOW, clockDevice.getSecondLight().getState());

        setBerlinClockTime("00:00:01");
        assertEquals(OFF, clockDevice.getSecondLight().getState());

        setBerlinClockTime("00:00:02");
        assertEquals(YELLOW, clockDevice.getSecondLight().getState());

        setBerlinClockTime("00:00:03");
        assertEquals(OFF, clockDevice.getSecondLight().getState());
    }

    /**
     * Check that top hours have 4 lamps
     *
     * @throws Exception
     */
    @Test
    public void topHoursLightsHaveFourLights() throws Exception {
        assertEquals(4, clockDevice.getTopHoursLights().size());
    }

    /**
     * Check that the lamps are lit red when enabled
     *
     * @throws Exception
     */
    @Test
    public void allEnabledTopHourLightsAreRed() throws Exception {
        setBerlinClockTime("20:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(clockDevice.getTopHoursLights())
        );
    }

    /**
     * Check that every top hour lamp represents 5 hours
     *
     * @throws Exception
     */
    @Test
    public void topHourLightsRepresentFiveHours() throws Exception {
        setBerlinClockTime("05:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, OFF, OFF, OFF)),
                toJson(clockDevice.getTopHoursLights())
        );

        setBerlinClockTime("12:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, OFF, OFF)),
                toJson(clockDevice.getTopHoursLights())
        );

        setBerlinClockTime("16:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, OFF)),
                toJson(clockDevice.getTopHoursLights())
        );

        setBerlinClockTime("23:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(clockDevice.getTopHoursLights())
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
        setBerlinClockTime("13:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, OFF)),
                toJson(clockDevice.getBottomHoursLights())
        );

        setBerlinClockTime("14:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, RED, RED)),
                toJson(clockDevice.getBottomHoursLights())
        );

        setBerlinClockTime("12:00:00");
        assertEquals(
                toJson(makeLightsByEnum(RED, RED, OFF, OFF)),
                toJson(clockDevice.getBottomHoursLights())
        );

        setBerlinClockTime("10:00:00");
        assertEquals(
                toJson(makeLightsByEnum(OFF, OFF, OFF, OFF)),
                toJson(clockDevice.getBottomHoursLights())
        );
    }

    /**
     * Check that top minutes have 11 lamps
     *
     * @throws Exception
     */
    @Test
    public void topMinutesHaveElevenLights() throws Exception {
        assertEquals(11, clockDevice.getTopMinutesLights().size());
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
        setBerlinClockTime("00:45:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, OFF, OFF)),
                toJson(clockDevice.getTopMinutesLights())
        );

        setBerlinClockTime("00:56:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, YELLOW)),
                toJson(clockDevice.getTopMinutesLights())
        );

        setBerlinClockTime("00:11:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF)),
                toJson(clockDevice.getTopMinutesLights())
        );

        setBerlinClockTime("00:20:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, RED, YELLOW, OFF, OFF, OFF, OFF, OFF, OFF, OFF)),
                toJson(clockDevice.getTopMinutesLights())
        );
    }

    /**
     * Check that bottom minutes have 4 lamps
     *
     * @throws Exception
     */
    @Test
    public void bottomMinutesHaveFourLights() throws Exception {
        assertEquals(4, clockDevice.getBottomMinutesLights().size());
    }

    /**
     * Check that bottom minutes represents 1 minute
     *
     * @throws Exception
     */
    @Test
    public void bottomMinuteLightsRepresentsOneMinute() throws Exception {
        setBerlinClockTime("00:04:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, YELLOW, YELLOW)),
                toJson(clockDevice.getBottomMinutesLights())
        );

        setBerlinClockTime("00:03:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, YELLOW, OFF)),
                toJson(clockDevice.getBottomMinutesLights())
        );

        setBerlinClockTime("00:02:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, YELLOW, OFF, OFF)),
                toJson(clockDevice.getBottomMinutesLights())
        );

        setBerlinClockTime("00:06:00");
        assertEquals(
                toJson(makeLightsByEnum(YELLOW, OFF, OFF, OFF)),
                toJson(clockDevice.getBottomMinutesLights())
        );
    }

    //TODO: maybe extract functions below to TestUtils or TestHelper class
    private static List<BerlinClockLight> makeLightsByEnum(BerlinClockLight.State... states) {
        final List<BerlinClockLight> result = new ArrayList<>(states.length);
        for (int i = 0; i < states.length; i++) {
            result.add(new BerlinClockLight(states[i]));
        }
        return result;
    }

    private static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    private void setBerlinClockTime(String sDate) {
        driver.setTime(timeParser.parseAsBerlinTime(sDate, FORMAT_INPUT_TIME));
    }
}