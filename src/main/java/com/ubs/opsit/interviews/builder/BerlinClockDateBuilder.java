package com.ubs.opsit.interviews.builder;

import com.ubs.opsit.interviews.domain.BerlinClock;

import java.util.Date;

/**
 * Created by Alexey on 08.10.2014.
 */
public interface BerlinClockDateBuilder {

    BerlinClock buildByDate(Date date);

}
