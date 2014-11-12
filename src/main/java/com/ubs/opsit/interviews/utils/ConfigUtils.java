package com.ubs.opsit.interviews.utils;

import com.ubs.opsit.interviews.domain.BerlinClockLight;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//TODO: maybe make Config singleton class
public class ConfigUtils {

    public static String getConfigProperty(String propertyName) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/com/ubs/opsit/interviews/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(propertyName);
    }


    public static Map<BerlinClockLight.State, String> buildRepresentationMap() {
        Map<BerlinClockLight.State, String> result = new HashMap<>();
        for (BerlinClockLight.State state : BerlinClockLight.State.values()) {
            result.put(state, getConfigProperty("displayState." + state.name()));
        }
        return result;
    }
}
