package com.ubs.opsit.interviews.domain;

import java.util.Arrays;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

/**
 * Created by Alexey on 07.10.2014.
 */
public class BerlinClock {
    //TODO: magic numbers
    BerlinClockLight secondLight = new BerlinClockLight(State.OFF);
    BerlinClockLight[] topHoursLights = new BerlinClockLight[4];
    BerlinClockLight[] bottomHoursLights = new BerlinClockLight[4];
    BerlinClockLight[] topMinutesLights = new BerlinClockLight[11];
    BerlinClockLight[] bottomMinutesLights = new BerlinClockLight[4];

    public BerlinClock() {
        adjustLightStates();
    }

    private void adjustLightStates() {
        //TODO: duplicate code
        Arrays.fill(topHoursLights, new BerlinClockLight(State.OFF));
        Arrays.fill(bottomHoursLights, new BerlinClockLight(State.OFF));
        Arrays.fill(topMinutesLights, new BerlinClockLight(State.OFF));
        Arrays.fill(bottomMinutesLights, new BerlinClockLight(State.OFF));
    }

    public void setStateTopHoursLights(int count, State state) {
        for (int i = 0; i < count; i++) {
            topHoursLights[i].setState(state);
        }
    }

    public void setStateSecondLight(State state) {
        secondLight.setState(state);
    }

    public BerlinClockLight getSecondLight(){
        return secondLight;
    }

    public BerlinClockLight[] getTopHoursLights() {
        return topHoursLights;
    }

    public BerlinClockLight[] getBottomHoursLights() {
        return bottomHoursLights;
    }

    public BerlinClockLight[] getTopMinutesLights() {
        return topMinutesLights;
    }

    public BerlinClockLight[] getBottomMinutesLights() {
        return bottomMinutesLights;
    }
}


