package com.ubs.opsit.interviews.utils;

import com.ubs.opsit.interviews.domain.BerlinClock;

/**
 * Created by Alexey on 07.10.2014.
 */
public class BerlinClockToStringConverter {
    private BerlinClock berlinClock;

    public BerlinClockToStringConverter(BerlinClock berlinClock) {
        this.berlinClock = berlinClock;
    }

    public String convertToString() {

        return berlinClock.toString(); //TODO or convert over here
    }

}
