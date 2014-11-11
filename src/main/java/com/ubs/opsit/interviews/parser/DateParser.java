package com.ubs.opsit.interviews.parser;

import com.ubs.opsit.interviews.domain.BerlinTime;

public interface DateParser {
    BerlinTime parseAsBerlinTime(String sDate, String formatDate);
}
