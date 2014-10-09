package com.ubs.opsit.interviews.domain;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

/**
 * Created by Alexey on 07.10.2014.
 */
public class BerlinClock {
    //TODO: magic numbers
    private final BerlinClockLight secondLight = new BerlinClockLight(State.OFF);
    private final BerlinClockLight[] topHoursLights = createAndInitArray(4, State.OFF);
    private final BerlinClockLight[] bottomHoursLights = createAndInitArray(4, State.OFF);
    private final BerlinClockLight[] topMinutesLights = createAndInitArray(11, State.OFF);
    private final BerlinClockLight[] bottomMinutesLights = createAndInitArray(4, State.OFF);

    public void setStateTopHourLightByIndex(int index, State state) {
        setStageForLightByIndex(index, state, topHoursLights);
    }

    public void setStateBottomHoursLightsRange(int index, State state) {
        setStageForLightByIndex(index, state, bottomHoursLights);
    }

    public void setStateTopMinutesLightByIndex(int index, State state) {
        setStageForLightByIndex(index, state, topMinutesLights);
    }

    public void setStateBottomMinutesLightByIndex(int index, State state) {
        setStageForLightByIndex(index, state, bottomMinutesLights);
    }
    public void setStageForLightByIndex(int index, State stage, BerlinClockLight[] lights){
        //TODO: check index
        lights[index].setState(stage);
    }

    private static BerlinClockLight[] createAndInitArray(int size, State state) {
        final BerlinClockLight[] result = new BerlinClockLight[size];
        for (int i = 0; i < result.length; i++) {
            result[i] = new BerlinClockLight(State.OFF);

        }
        return result;
    }

    public void setStateSecondLight(State state) {
        secondLight.setState(state);
    }

    public BerlinClockLight getSecondLight() {
        return secondLight;
    }

    public List<BerlinClockLight> getTopHoursLights() {
        return Collections.unmodifiableList(Arrays.asList(topHoursLights));
    }

    public List<BerlinClockLight> getBottomHoursLights() {
        return Collections.unmodifiableList(Arrays.asList(bottomHoursLights));
    }

    public List<BerlinClockLight> getTopMinutesLights() {
        return Collections.unmodifiableList(Arrays.asList(topMinutesLights));
    }

    public List<BerlinClockLight> getBottomMinutesLights() {
        return Collections.unmodifiableList(Arrays.asList(bottomMinutesLights));
    }

    @Override
    public String toString() {
        return "BerlinClock{" +
                "secondLight=" + secondLight +
                ", topHoursLights=" + Arrays.toString(topHoursLights) +
                ", bottomHoursLights=" + Arrays.toString(bottomHoursLights) +
                ", topMinutesLights=" + Arrays.toString(topMinutesLights) +
                ", bottomMinutesLights=" + Arrays.toString(bottomMinutesLights) +
                '}';
    }
}


