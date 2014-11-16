package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinClockLight;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BerlinClockSerializerImpl implements BerlinClockSerializer {
    private final Map<BerlinClockLight.State, String> representationMap;
    private final String separator;

    public BerlinClockSerializerImpl(Map<BerlinClockLight.State, String> representationMap, String separator) {
        this.representationMap = representationMap;
        this.separator = separator;
    }

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
        sb.append(separator);
    }

    protected String representState(BerlinClockLight.State state) {
        final String representation = representationMap.get(state);
        if (representation == null) {
            throw new RuntimeException("Cannot represent state " + state.name());
        }
        return representation;
    }
}
