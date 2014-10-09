package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClock;

public interface BerlinClockSerializer {
    String serializeAsString(BerlinClock berlinClock);
}
