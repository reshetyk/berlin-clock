package com.ubs.opsit.interviews.parser;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeParser;
import org.joda.time.format.DateTimePrinter;

/**
 * @author Alexey
 */
public class DateTimeFormatNew extends DateTimeFormatter {

    /**
     * Creates a new formatter, however you will normally use the factory
     * or the builder.
     *
     * @param printer the internal printer, null if cannot print
     * @param parser  the internal parser, null if cannot parse
     */
    public DateTimeFormatNew(DateTimePrinter printer, DateTimeParser parser) {
        super(printer, parser);
    }


}
