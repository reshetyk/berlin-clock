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

    BerlinClockSerializer berlinClockSerializer;

    @Before
    public void setUp() throws Exception {
        berlinClockSerializer = new BerlinClockSerializerImpl(ConfigUtils.buildRepresentationMap(), "\n");
    }

    @Test
    public void serializeAsStringEmptyBerlinClock() throws Exception {
        BerlinClockDevice emptyBerlinClockDevice = new BerlinClockDevice();
        assertEquals("O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", berlinClockSerializer.serializeAsString(emptyBerlinClockDevice));

    }

    @Test
    public void serializeAsStringSeconds() throws Exception {
        BerlinClockDevice secondLightOff = spy(new BerlinClockDevice());
        when(secondLightOff.getSecondLight()).thenReturn(new BerlinClockLight(State.OFF));

        BerlinClockDevice secondLightYellow = spy(new BerlinClockDevice());
        when(secondLightYellow.getSecondLight()).thenReturn(new BerlinClockLight(State.YELLOW));

        //TODO: check only seconds
        assertEquals("O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", berlinClockSerializer.serializeAsString(secondLightOff));
        assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", berlinClockSerializer.serializeAsString(secondLightYellow));

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
        assertEquals("O\nRRRR\nOOOO\nOOOOOOOOOOO\nOOOO", berlinClockSerializer.serializeAsString(topHoursLightAllRed));

    }

    //...


}