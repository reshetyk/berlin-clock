package com.ubs.opsit.interviews.driver;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinTime;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

public class BerlinClockDriverImpl implements BerlinClockDriver {

    private BerlinClockDevice berlinClockDevice;

    public BerlinClockDriverImpl(BerlinClockDevice berlinClockDevice) {
        setupDevice(berlinClockDevice);
    }

    @Override
    public void setupDevice(BerlinClockDevice berlinClockDevice) {
        this.berlinClockDevice = berlinClockDevice;
    }

    @Override
    public void setTime(BerlinTime berlinTime) {
        resetClock();
        setSecondLight(berlinTime);
        setTopHoursLights(berlinTime);
        setBottomHoursLights(berlinTime);
        setTopMinutesLights(berlinTime);
        setBottomMinutesLights(berlinTime);
    }

    protected void resetClock() {
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

    protected void setSecondLight(BerlinTime berlinTime) {
        if (berlinTime.getSeconds() % 2 == 0) {
            berlinClockDevice.setStateSecondLight(State.YELLOW);
        } else {
            berlinClockDevice.setStateSecondLight(State.OFF);
        }
    }

    protected void setTopHoursLights(BerlinTime berlinTime) {
        for (int i = 0; i < countEnabledTopLights(berlinTime.getHours()); i++) {
            berlinClockDevice.setStateTopHourLightByIndex(i, State.RED);
        }
    }

    protected void setBottomHoursLights(BerlinTime berlinTime) {
        for (int i = 0; i < countEnabledBottomLights(berlinTime.getHours()); i++) {
            berlinClockDevice.setStateBottomHoursLightsByIndex(i, State.RED);
        }
    }

    protected void setTopMinutesLights(BerlinTime berlinTime) {
        for (int i = 0; i < countEnabledTopLights(berlinTime.getMinutes()); i++) {
            berlinClockDevice.setStateTopMinutesLightByIndex(i, (i + 1) % 3 == 0 ? State.RED : State.YELLOW);
        }
    }

    protected void setBottomMinutesLights(BerlinTime berlinTime) {
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
