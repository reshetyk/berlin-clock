package com.ubs.opsit.interviews.driver;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinTime;

public interface BerlinClockDriver {

    void setupDevice(BerlinClockDevice berlinClockDevice);

    void setTime(BerlinTime berlinTime);

}
