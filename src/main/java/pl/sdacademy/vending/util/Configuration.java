package pl.sdacademy.vending.util;

public interface Configuration {
    String getProperty(String propertyName, String defeaultValue);
    Long getProperty(String propertyName, Long defeaultValue);
}
