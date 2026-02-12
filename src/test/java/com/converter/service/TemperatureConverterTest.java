package com.converter.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    @Test
    public void testCelsiusToFahrenheit_Zero() {
        double result = TemperatureConverter.celsiusToFahrenheit(0);
        assertEquals(32.0, result, 0.01);
    }

    @Test
    public void testCelsiusToFahrenheit_Positive() {
        double result = TemperatureConverter.celsiusToFahrenheit(100);
        assertEquals(212.0, result, 0.01);
    }

    @Test
    public void testCelsiusToFahrenheit_Negative() {
        double result = TemperatureConverter.celsiusToFahrenheit(-40);
        assertEquals(-40.0, result, 0.01);
    }

    @Test
    public void testCelsiusToFahrenheit_Decimal() {
        double result = TemperatureConverter.celsiusToFahrenheit(37.5);
        assertEquals(99.5, result, 0.01);
    }

    @Test
    public void testCelsiusToFahrenheit_BelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.celsiusToFahrenheit(-300);
        });
    }

    @Test
    public void testFahrenheitToCelsius_Zero() {
        double result = TemperatureConverter.fahrenheitToCelsius(32);
        assertEquals(0.0, result, 0.01);
    }

    @Test
    public void testFahrenheitToCelsius_Freezing() {
        double result = TemperatureConverter.fahrenheitToCelsius(212);
        assertEquals(100.0, result, 0.01);
    }

    @Test
    public void testFahrenheitToCelsius_BelowAbsoluteZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            TemperatureConverter.fahrenheitToCelsius(-500);
        });
    }
}
