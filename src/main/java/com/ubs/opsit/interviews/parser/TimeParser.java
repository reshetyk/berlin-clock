package com.ubs.opsit.interviews.parser;

import com.ubs.opsit.interviews.domain.BerlinTime;

public interface TimeParser {
    BerlinTime parseAsBerlinTime(String sDate, String formatDate);
}