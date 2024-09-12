package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationServiceTest {
    private final LocationService locationService = new LocationService();

    @Test
    public void testCabLocationWithSystemOut() {
        String mockLocation = "cab-location, cab-location-2";

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        locationService.cabLocation(mockLocation);

        assertEquals("cab-book-usercab-location, cab-location-2\r\n", outContent.toString());
    }
}