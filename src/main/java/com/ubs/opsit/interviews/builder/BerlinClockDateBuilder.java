package com.ubs.opsit.interviews.builder;

import com.ubs.opsit.interviews.domain.BerlinClock;

import java.util.Date;

public interface BerlinClockDateBuilder {

    BerlinClock buildByDate(Date date);

}
