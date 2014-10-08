package com.ubs.opsit.interviews.builder;

import com.ubs.opsit.interviews.domain.BerlinClock;
import org.joda.time.DateTime;

import java.util.Date;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

/**
 * Created by Alexey on 07.10.2014.
 */
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
        berlinClock.setStateTopHoursLights(defineCountTopLights(hours), State.RED);
    }

    private int defineCountTopLights(int number) {
        return (number - (number % 5)) / 5;
    }

    private void parseDate(Date date) {
        DateTime dateTime = new DateTime(date);
        this.seconds = dateTime.getSecondOfMinute();
        this.minutes = dateTime.getMinuteOfHour();
        this.hours = dateTime.getMinuteOfHour();
    }

    //...
}
