package com.ubs.opsit.interviews.utils;

import com.ubs.opsit.interviews.service.exception.TimeConverterException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Utils {
    //TODO: maybe make Config singleton class
    public static String getConfigProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/com/ubs/opsit/interviews/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }


}
