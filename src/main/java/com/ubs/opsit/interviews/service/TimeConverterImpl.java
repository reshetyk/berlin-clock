package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.domain.BerlinTime;
import com.ubs.opsit.interviews.driver.BerlinClockDriver;
import com.ubs.opsit.interviews.driver.BerlinClockDriverImpl;
import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.parser.DateParser;
import com.ubs.opsit.interviews.parser.DateParserImpl;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializer;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializerImpl;
import com.ubs.opsit.interviews.utils.Utils;

public class TimeConverterImpl implements TimeConverter {
    private final String inputTimeFormat;
    private final BerlinClockDevice berlinClockDevice;
    private final BerlinClockDriver berlinClockDriver;
    private final BerlinClockSerializer berlinClockSerializer;
    private final DateParser dateParser;


    public TimeConverterImpl(String inputTimeFormat, BerlinClockDevice berlinClockDevice, BerlinClockDriver berlinClockDriver, BerlinClockSerializer berlinClockSerializer, DateParser dateParser) {
        this.inputTimeFormat = inputTimeFormat;
        this.berlinClockDevice = berlinClockDevice;
        this.berlinClockDriver = berlinClockDriver;
        this.berlinClockSerializer = berlinClockSerializer;
        this.dateParser = dateParser;
    }

    /**
     * dependencies will be created by default automatically
     *
     * @return TimeConverterImpl
     */
    //TODO: maybe is not the best way, think about it
    public static TimeConverterImpl getDefaultInstance(){
        return new TimeConverterImpl(
                Utils.getConfigProperty("inputTimeFormat"),
                new BerlinClockDevice(),
                new BerlinClockDriverImpl(),
                new BerlinClockSerializerImpl(),
                new DateParserImpl()
        );
    }

    @Override
    public String convertTime(String sInputTime) {
        final BerlinTime berlinTime = dateParser.parseAsBerlinTime(sInputTime, inputTimeFormat);

        berlinClockDriver.setTimeOnBerlinClockDevice(berlinClockDevice, berlinTime);

        return berlinClockSerializer.serializeAsString(berlinClockDevice);
    }




}
