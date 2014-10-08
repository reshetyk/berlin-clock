package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClock;
import com.ubs.opsit.interviews.domain.BerlinClockLight;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexey on 07.10.2014.
 */
public class BerlinClockSerializerImpl implements BerlinClockSerializer {

    @Override
    public String serializeAsString(BerlinClock berlinClock) {
        StringBuilder sb = new StringBuilder();
        serializeLights(Arrays.asList(berlinClock.getSecondLight()), sb);
        serializeLights(berlinClock.getTopHoursLights(), sb);
        serializeLights(berlinClock.getBottomHoursLights(), sb);
        serializeLights(berlinClock.getTopMinutesLights(), sb);
        serializeLights(berlinClock.getBottomMinutesLights(), sb);

        return sb.toString().trim();
    }

    protected void serializeLights (List<BerlinClockLight> lights, StringBuilder sb) {
        for (BerlinClockLight light : lights) {
            sb.append(representState(light.getState()));
        }
        sb.append('\n');
    }

    protected String representState (BerlinClockLight.State state) {
        //TODO: should be obtained from config file
        if (state == BerlinClockLight.State.YELLOW) return "Y";
        if (state == BerlinClockLight.State.RED) return "R";
        if (state == BerlinClockLight.State.OFF) return "O";
        throw new RuntimeException("Cannot represent state " + state.name());
    }

}
