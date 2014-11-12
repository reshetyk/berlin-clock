package com.ubs.opsit.interviews.service;

import com.ubs.opsit.interviews.domain.BerlinClockDevice;
import com.ubs.opsit.interviews.domain.BerlinTime;
import com.ubs.opsit.interviews.driver.BerlinClockDriver;
import com.ubs.opsit.interviews.driver.BerlinClockDriverImpl;
import com.ubs.opsit.interviews.parser.TimeParser;
import com.ubs.opsit.interviews.parser.TimeParserImpl;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializer;
import com.ubs.opsit.interviews.serializer.BerlinClockSerializerImpl;
import com.ubs.opsit.interviews.utils.ConfigUtils;

public class StringRepresentationConverter implements TimeConverter {
    private final String inputTimeFormat;
    private final BerlinClockDevice berlinClockDevice;
    private final BerlinClockDriver berlinClockDriver;
    private final BerlinClockSerializer berlinClockSerializer;
    private final TimeParser timeParser;


    public StringRepresentationConverter(String inputTimeFormat, BerlinClockDevice berlinClockDevice, BerlinClockDriver berlinClockDriver, BerlinClockSerializer berlinClockSerializer, TimeParser timeParser) {
        this.inputTimeFormat = inputTimeFormat;
        this.berlinClockDevice = berlinClockDevice;
        this.berlinClockDriver = berlinClockDriver;
        this.berlinClockSerializer = berlinClockSerializer;
        this.timeParser = timeParser;
    }

    /**
     * dependencies will be created by default automatically
     *
     * @return TimeConverterImpl
     */
    //TODO: maybe is not the best way, think about it
    public static StringRepresentationConverter getDefaultInstance() {
        final BerlinClockDevice clockDevice = new BerlinClockDevice();
        return new StringRepresentationConverter(
                ConfigUtils.getConfigProperty("inputTimeFormat"),
                clockDevice,
                new BerlinClockDriverImpl(clockDevice),
                new BerlinClockSerializerImpl(),
                new TimeParserImpl()
        );
    }

    @Override
    public String convertTime(String sInputTime) {
        final BerlinTime berlinTime = timeParser.parseAsBerlinTime(sInputTime, inputTimeFormat);

        berlinClockDriver.setTime(berlinTime);

        return berlinClockSerializer.serializeAsString(berlinClockDevice);
    }


}
