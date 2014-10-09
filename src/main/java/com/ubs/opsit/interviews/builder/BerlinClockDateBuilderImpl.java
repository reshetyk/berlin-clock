package com.ubs.opsit.interviews.builder;

import com.ubs.opsit.interviews.domain.BerlinClock;
import org.joda.time.DateTime;

import java.util.Date;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

public class BerlinClockDateBuilderImpl implements BerlinClockDateBuilder {

    private BerlinClock berlinClock;
    private int seconds;
    private int minutes;
    private int hours;

    @Override
    public BerlinClock buildByDate(Date date) {
        this.berlinClock = new BerlinClock();
        parseDate(date);
        setUpSecondLight();
        setUpTopHoursLights();
        setUpBottomHoursLights();
        setUpTopMinutesLights();
        setUpBottomMinutesLights();
        return berlinClock;
    }

    protected void setUpSecondLight() {
        if (seconds % 2 == 0) {
            berlinClock.setStateSecondLight(State.YELLOW);
        } else {
            berlinClock.setStateSecondLight(State.OFF);
        }
    }

    protected void setUpTopHoursLights() {
        for (int i = 0; i < countEnabledTopLights(hours); i++) {
            berlinClock.setStateTopHourLightByIndex(i, State.RED);
        }
    }

    protected void setUpBottomHoursLights() {
        for (int i = 0; i < countEnabledBottomLights(hours); i++) {
            berlinClock.setStateBottomHoursLightsRange(i, State.RED);
        }
    }

    protected void setUpTopMinutesLights() {
        for (int i = 0; i < countEnabledTopLights(minutes); i++) {
            berlinClock.setStateTopMinutesLightByIndex(i, (i + 1) % 3 == 0 ? State.RED : State.YELLOW);
        }
    }

    protected void setUpBottomMinutesLights() {
        for (int i = 0; i < countEnabledBottomLights(minutes); i++) {
            berlinClock.setStateBottomMinutesLightByIndex(i, State.YELLOW);
        }
    }

    private int countEnabledTopLights(int number) {
        return (number - (number % 5)) / 5;
    }
    private int countEnabledBottomLights(int number) {
        return (number % 5);
    }

    private void parseDate(Date date) {
        DateTime dateTime = new DateTime(date);
        this.seconds = dateTime.getSecondOfMinute();
        this.minutes = dateTime.getMinuteOfHour();
        this.hours = dateTime.getHourOfDay();
    }
}
