package pl.sdacademy.vending.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static final String PROPERTIES_FILE_LOCATION = "application.properties";
    private final Properties properties;

    private Configuration() {
        properties = new Properties();

        try (InputStream propertiesFile = ClassLoader
                .getSystemResourceAsStream(PROPERTIES_FILE_LOCATION)) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public String getProperty(String propertyName, String defeaultValue) {
        return properties.getProperty(propertyName, defeaultValue);
    }

    public Long getProperty(String propertyName, Long defeaultValue) {
        String requestedValue = properties.getProperty(propertyName);
        if (requestedValue != null) {
            long value = Long.parseLong(requestedValue);
            return value;
        } else {
            return defeaultValue;
        }
    }

    private static class InstanceHolder {
        private static final Configuration INSTANCE = new Configuration();
    }
}
