package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializerImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


public class BerlinClockSerializerTest {

    @Test
    public void serializeAsStringEmptyBerlinClock() throws Exception {
        BerlinClock emptyBerlinClock = new BerlinClock();
        assertEquals("O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", new BerlinClockSerializerImpl().serializeAsString(emptyBerlinClock));

    }

    @Test
    public void serializeAsStringSeconds() throws Exception {
        BerlinClock secondLightOff = spy(new BerlinClock());
        when(secondLightOff.getSecondLight()).thenReturn(new BerlinClockLight(State.OFF));

        BerlinClock secondLightYellow = spy(new BerlinClock());
        when(secondLightYellow.getSecondLight()).thenReturn(new BerlinClockLight(State.YELLOW));

        assertEquals("O\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", new BerlinClockSerializerImpl().serializeAsString(secondLightOff));
        assertEquals("Y\nOOOO\nOOOO\nOOOOOOOOOOO\nOOOO", new BerlinClockSerializerImpl().serializeAsString(secondLightYellow));

    }

    @Test
    public void serializeAsStringTopHours() throws Exception {
        BerlinClock topHoursLightAllRed = spy(new BerlinClock());
        List<BerlinClockLight> berlinClockLights = Arrays.asList(new BerlinClockLight(State.RED),
                                                                 new BerlinClockLight(State.RED),
                                                                 new BerlinClockLight(State.RED),
                                                                 new BerlinClockLight(State.RED));

        when(topHoursLightAllRed.getTopHoursLights()).thenReturn(berlinClockLights);


        assertEquals("O\nRRRR\nOOOO\nOOOOOOOOOOO\nOOOO", new BerlinClockSerializerImpl().serializeAsString(topHoursLightAllRed));

    }

    //...


}