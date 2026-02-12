package com.converter.service;

public class TemperatureConverter {

    /**
     * Converts temperature from Celsius to Fahrenheit
     * Formula: F = (C × 9/5) + 32
     * @param celsius the temperature in Celsius
     * @return the temperature in Fahrenheit
     * @throws IllegalArgumentException if celsius is less than absolute zero (-273.15°C)
     */
    public static double celsiusToFahrenheit(double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (-273.15°C)");
        }
        return (celsius * 9.0 / 5.0) + 32;
    }

    /**
     * Converts temperature from Fahrenheit to Celsius
     * Formula: C = (F - 32) × 5/9
     * @param fahrenheit the temperature in Fahrenheit
     * @return the temperature in Celsius
     * @throws IllegalArgumentException if fahrenheit is less than absolute zero (-459.67°F)
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        if (fahrenheit < -459.67) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero (-459.67°F)");
        }
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}
