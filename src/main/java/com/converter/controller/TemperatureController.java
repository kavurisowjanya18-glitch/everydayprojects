package com.converter.controller;

import com.converter.dto.ConversionResponse;
import com.converter.service.TemperatureConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TemperatureController {

    /**
     * Converts Celsius to Fahrenheit
     * @param celsius the temperature in Celsius
     * @return ConversionResponse with both celsius and fahrenheit values
     */
    @GetMapping("/convert")
    public ResponseEntity<?> convertCelsiusToFahrenheit(@RequestParam double celsius) {
        try {
            double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
            return ResponseEntity.ok(new ConversionResponse(celsius, fahrenheit));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }

    /**
     * Health check endpoint
     * @return status message
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Temperature Converter API is running");
    }

    /**
     * Inner class for error responses
     */
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
