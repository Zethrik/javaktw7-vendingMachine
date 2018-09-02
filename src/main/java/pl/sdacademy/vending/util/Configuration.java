package pl.sdacademy.vending.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final String PROPERTIES_FILE_LOCATION = "application.properties";
    private final Properties properties;

    public Configuration() {
        properties = new Properties();

        try (InputStream propertiesFile = ClassLoader
                .getSystemResourceAsStream(PROPERTIES_FILE_LOCATION)) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty (String propertyName, String defeaultValue) {
        return properties.getProperty(propertyName, defeaultValue);
    }

    public Long getProperty (String propertyName, Long defeaultValue) {
        String requestedValue = properties.getProperty(propertyName);
        if (requestedValue != null) {
            return Long.parseLong(requestedValue);
        } else {
            return defeaultValue;
        }
    }
}
