package com.ubs.opsit.interviews.serializer;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;

public interface BerlinClockSerializer {
    String serializeAsString(BerlinClockDevice berlinClockDevice);
}
