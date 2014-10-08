package com.ubs.opsit.interviews.utils;

import com.ubs.opsit.interviews.domain.BerlinClock;
import org.joda.time.DateTime;

import java.util.Date;

import static com.ubs.opsit.interviews.domain.BerlinClockLight.State;

/**
 * Created by Alexey on 07.10.2014.
 */
public class DateToBerlinClockConverter {

    private final BerlinClock berlinClock;

    private int seconds;
    private int minutes;
    private int hours;

    public DateToBerlinClockConverter(Date date) {
        this(date, new BerlinClock());
    }

    public DateToBerlinClockConverter(Date date, BerlinClock berlinClock) {
        this.berlinClock = berlinClock;
        parseDate(date);
    }

    private void parseDate(Date date) {
        DateTime dateTime = new DateTime(date);
        this.seconds = dateTime.getSecondOfMinute();
        this.minutes = dateTime.getMinuteOfHour();
        this.hours = dateTime.getMinuteOfHour();
    }

    public BerlinClock convert() {
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
    //...
}
