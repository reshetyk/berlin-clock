package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinClockLight;
import com.ubs.opsit.interviews.utils.ConfigUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BerlinClockSerializerImpl implements BerlinClockSerializer {

    private Map<BerlinClockLight.State, String> representationMap;

    public BerlinClockSerializerImpl() {
        this.representationMap = ConfigUtils.buildRepresentationMap();
    }

    public BerlinClockSerializerImpl(Map<BerlinClockLight.State, String> representationMap) {
        this.representationMap = representationMap;
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
        sb.append('\n');//TODO: must not be hardcoded
    }

    protected String representState(BerlinClockLight.State state) {
        final String representation = representationMap.get(state);
        if (representation == null) {
            throw new RuntimeException("Cannot represent state " + state.name());
        }
        return representation ;
    }

}
