package com.converter.controller;

import com.converter.TemperatureConverterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = TemperatureConverterApplication.class)
@AutoConfigureMockMvc
public class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConvertCelsiusToFahrenheit_Zero() throws Exception {
        mockMvc.perform(get("/api/convert").param("celsius", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.celsius").value(0.0))
                .andExpect(jsonPath("$.fahrenheit").value(32.0));
    }

    @Test
    public void testConvertCelsiusToFahrenheit_BoilingPoint() throws Exception {
        mockMvc.perform(get("/api/convert").param("celsius", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.celsius").value(100.0))
                .andExpect(jsonPath("$.fahrenheit").value(212.0));
    }

    @Test
    public void testConvertCelsiusToFahrenheit_NegativeValue() throws Exception {
        mockMvc.perform(get("/api/convert").param("celsius", "-40"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.celsius").value(-40.0))
                .andExpect(jsonPath("$.fahrenheit").value(-40.0));
    }

    @Test
    public void testConvertCelsiusToFahrenheit_BelowAbsoluteZero() throws Exception {
        mockMvc.perform(get("/api/convert").param("celsius", "-300"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    public void testConvertCelsiusToFahrenheit_InvalidInput() throws Exception {
        mockMvc.perform(get("/api/convert").param("celsius", "invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Temperature Converter API is running"));
    }
}
