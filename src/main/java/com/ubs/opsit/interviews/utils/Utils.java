package com.ubs.opsit.interviews.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Alexey on 08.10.2014.
 */
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
