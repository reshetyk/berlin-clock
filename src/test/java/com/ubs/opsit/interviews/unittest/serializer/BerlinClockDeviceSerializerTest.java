package com.ubs.opsit.interviews.unittest.serializer;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializer;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializerImpl;
import com.ubs.opsit.interviews.utils.ConfigUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class BerlinClockDeviceSerializerTest {

    private static final String SEPARATOR = "\n";
    private BerlinClockSerializer berlinClockSerializer;

    @Before
    public void setUp() throws Exception {
        berlinClockSerializer = new BerlinClockSerializerImpl(ConfigUtils.buildRepresentationMap(), SEPARATOR);
    }

    @Test
    public void serializeAsStringEmptyBerlinClock() throws Exception {
        BerlinClockDevice emptyBerlinClockDevice = new BerlinClockDevice();
        assertEquals("O" + SEPARATOR + "OOOO" + SEPARATOR + "OOOO" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                berlinClockSerializer.serializeAsString(emptyBerlinClockDevice));
    }

    @Test
    public void serializeAsStringSeconds() throws Exception {
        BerlinClockDevice secondLightOff = spy(new BerlinClockDevice());
        when(secondLightOff.getSecondLight()).thenReturn(new BerlinClockLight(State.OFF));

        BerlinClockDevice secondLightYellow = spy(new BerlinClockDevice());
        when(secondLightYellow.getSecondLight()).thenReturn(new BerlinClockLight(State.YELLOW));

        //TODO: check only seconds
        assertEquals("O" + SEPARATOR + "OOOO" + SEPARATOR + "OOOO" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                berlinClockSerializer.serializeAsString(secondLightOff));

        assertEquals("Y" + SEPARATOR + "OOOO" + SEPARATOR + "OOOO" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                berlinClockSerializer.serializeAsString(secondLightYellow));

    }

    @Test
    public void serializeAsStringTopHours() throws Exception {
        BerlinClockDevice topHoursLightAllRed = spy(new BerlinClockDevice());
        List<BerlinClockLight> berlinClockLights = Arrays.asList(new BerlinClockLight(State.RED),
                new BerlinClockLight(State.RED),
                new BerlinClockLight(State.RED),
                new BerlinClockLight(State.RED));

        when(topHoursLightAllRed.getTopHoursLights()).thenReturn(berlinClockLights);

        //TODO: check only top hours
        assertEquals("O" + SEPARATOR + "RRRR" + SEPARATOR + "OOOO" + SEPARATOR + "OOOOOOOOOOO" + SEPARATOR + "OOOO",
                berlinClockSerializer.serializeAsString(topHoursLightAllRed));

    }

    //serializeAsStringBottomHours() and so on... TODO: complete me


}