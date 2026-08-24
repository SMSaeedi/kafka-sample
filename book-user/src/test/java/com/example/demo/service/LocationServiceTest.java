package com.example.demo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LocationService Unit Tests")
class LocationServiceTest {
    private LocationService locationService;
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        locationService = new LocationService();
        originalOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Should print cab-book-user prefix with location message")
    void testCabLocationWithValidInput() {
        String mockLocation = "cab-location, cab-location-2";
        locationService.cabLocation(mockLocation);
        
        String output = outContent.toString().trim();
        assertTrue(output.contains("cab-book-user"), "Output should contain cab-book-user prefix");
        assertTrue(output.contains(mockLocation), "Output should contain the location message");
    }

    @Test
    @DisplayName("Should handle empty location string")
    void testCabLocationWithEmptyString() {
        locationService.cabLocation("");
        
        String output = outContent.toString().trim();
        assertEquals("cab-book-user", output, "Should print prefix with empty location");
    }

    @Test
    @DisplayName("Should handle null location gracefully")
    void testCabLocationWithNull() {
        assertDoesNotThrow(() -> locationService.cabLocation(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "Location: 10.5,20.3",
        "Driver at 42.3601,-71.0589",
        "Lat: 51.5074, Lon: -0.1278",
        "Multiple,Comma,Separated,Values"
    })
    @DisplayName("Should handle various location formats")
    void testCabLocationWithVariousFormats(String location) {
        locationService.cabLocation(location);
        
        String output = outContent.toString().trim();
        assertTrue(output.startsWith("cab-book-user"), "Output should start with cab-book-user");
        assertTrue(output.contains(location), "Output should contain the location input");
    }

    @Test
    @DisplayName("Should handle location with special characters")
    void testCabLocationWithSpecialCharacters() {
        String specialLocation = "Location@#$%^&*()";
        locationService.cabLocation(specialLocation);
        
        String output = outContent.toString().trim();
        assertTrue(output.contains(specialLocation), "Should handle special characters");
    }

    @Test
    @DisplayName("Should handle location with line breaks")
    void testCabLocationWithLineBreaks() {
        String locationWithNewline = "Location1\nLocation2";
        locationService.cabLocation(locationWithNewline);
        
        assertFalse(outContent.toString().isEmpty(), "Should output something");
    }

    @Test
    @DisplayName("Should handle very long location string")
    void testCabLocationWithLongString() {
        String longLocation = "A".repeat(1000);
        locationService.cabLocation(longLocation);
        
        String output = outContent.toString().trim();
        assertTrue(output.contains("cab-book-user"), "Should handle long strings");
    }

    @Test
    @DisplayName("Should be invoked multiple times in sequence")
    void testCabLocationMultipleInvocations() {
        locationService.cabLocation("Location1");
        locationService.cabLocation("Location2");
        locationService.cabLocation("Location3");
        
        String output = outContent.toString();
        assertTrue(output.contains("Location1"), "Should contain first location");
        assertTrue(output.contains("Location2"), "Should contain second location");
        assertTrue(output.contains("Location3"), "Should contain third location");
    }
}