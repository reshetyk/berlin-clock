package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinClockLight;

import java.util.Arrays;
import java.util.List;

public class BerlinClockSerializerImpl implements BerlinClockSerializer {

    @Override
    public String serializeAsString(BerlinClockDevice berlinClockDevice) {
        StringBuilder sb = new StringBuilder();
        serializeLights(Arrays.asList(berlinClockDevice.getSecondLight()), sb);
        serializeLights(berlinClockDevice.getTopHoursLights(), sb);
        serializeLights(berlinClockDevice.getBottomHoursLights(), sb);
        serializeLights(berlinClockDevice.getTopMinutesLights(), sb);
        serializeLights(berlinClockDevice.getBottomMinutesLights(), sb);

        return sb.toString().trim();
    }

    protected void serializeLights(List<BerlinClockLight> lights, StringBuilder sb) {
        for (BerlinClockLight light : lights) {
            sb.append(representState(light.getState()));
        }
        sb.append('\n');
    }

    protected String representState(BerlinClockLight.State state) {
        //TODO: should be obtained from config file
        switch (state) {
            case YELLOW:
                return "Y";
            case RED:
                return "R";
            case OFF:
                return "O";
            default:
                throw new RuntimeException("Cannot represent state " + state.name());
        }
    }

}
