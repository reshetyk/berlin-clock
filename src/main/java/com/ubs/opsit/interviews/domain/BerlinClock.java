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

    private static BerlinClockLight[] createAndInitArray(int size, State state) {
        final BerlinClockLight[] result = new BerlinClockLight[size];
        Arrays.fill(result, new BerlinClockLight(state));
        return result;
    }

    public void setStateTopHoursLights(int count, State state) {
        for (int i = 0; i < count; i++) {
            topHoursLights[i].setState(state);
        }
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


