package com.ubs.opsit.interviews.driver;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinTime;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

public class BerlinClockDriverImpl implements BerlinClockDriver {

    @Override
    public void setTimeOnBerlinClockDevice(BerlinClockDevice berlinClockDevice, BerlinTime berlinTime) {
        resetClock(berlinClockDevice);
        setUpSecondLight(berlinTime, berlinClockDevice);
        setUpTopHoursLights(berlinTime, berlinClockDevice);
        setUpBottomHoursLights(berlinTime, berlinClockDevice);
        setUpTopMinutesLights(berlinTime, berlinClockDevice);
        setUpBottomMinutesLights(berlinTime, berlinClockDevice);
    }

    protected static void resetClock(BerlinClockDevice berlinClockDevice) {
        berlinClockDevice.setStateSecondLight(State.OFF);
        //TODO: find the way to do it easier
        for (int i = 0; berlinClockDevice.getTopHoursLights().size() > i; i++) {
            berlinClockDevice.setStateTopHourLightByIndex(i, State.OFF);
        }
        for (int i = 0; berlinClockDevice.getBottomHoursLights().size() > i; i++) {
            berlinClockDevice.setStateBottomHoursLightsByIndex(i, State.OFF);
        }
        for (int i = 0; berlinClockDevice.getTopMinutesLights().size() > i; i++) {
            berlinClockDevice.setStateTopMinutesLightByIndex(i, State.OFF);
        }
        for (int i = 0; berlinClockDevice.getBottomMinutesLights().size() > i; i++) {
            berlinClockDevice.setStateBottomMinutesLightByIndex(i, State.OFF);
        }

    }

    protected static void setUpSecondLight(BerlinTime berlinTime, BerlinClockDevice berlinClockDevice) {
        if (berlinTime.getSeconds() % 2 == 0) {
            berlinClockDevice.setStateSecondLight(State.YELLOW);
        } else {
            berlinClockDevice.setStateSecondLight(State.OFF);
        }
    }

    protected static void setUpTopHoursLights(BerlinTime berlinTime, BerlinClockDevice berlinClockDevice) {
        for (int i = 0; i < countEnabledTopLights(berlinTime.getHours()); i++) {
            berlinClockDevice.setStateTopHourLightByIndex(i, State.RED);
        }
    }

    protected static void setUpBottomHoursLights(BerlinTime berlinTime, BerlinClockDevice berlinClockDevice) {
        for (int i = 0; i < countEnabledBottomLights(berlinTime.getHours()); i++) {
            berlinClockDevice.setStateBottomHoursLightsByIndex(i, State.RED);
        }
    }

    protected static void setUpTopMinutesLights(BerlinTime berlinTime, BerlinClockDevice berlinClockDevice) {
        for (int i = 0; i < countEnabledTopLights(berlinTime.getMinutes()); i++) {
            berlinClockDevice.setStateTopMinutesLightByIndex(i, (i + 1) % 3 == 0 ? State.RED : State.YELLOW);
        }
    }

    protected static void setUpBottomMinutesLights(BerlinTime berlinTime, BerlinClockDevice berlinClockDevice) {
        for (int i = 0; i < countEnabledBottomLights(berlinTime.getMinutes()); i++) {
            berlinClockDevice.setStateBottomMinutesLightByIndex(i, State.YELLOW);
        }
    }

    private static int countEnabledTopLights(int number) {
        return (number - (number % 5)) / 5;
    }

    private static int countEnabledBottomLights(int number) {
        return (number % 5);
    }

}
