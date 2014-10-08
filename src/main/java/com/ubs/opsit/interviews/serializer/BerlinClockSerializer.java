package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClock;

/**
 * Created by Alexey on 08.10.2014.
 */
public interface BerlinClockSerializer {
    String serializeAsString(BerlinClock berlinClock);
}
