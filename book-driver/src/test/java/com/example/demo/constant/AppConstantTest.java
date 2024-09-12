package com.example.demo.constant;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppConstantTest {
    @Test
    public void testCabLocationConstant() {
        String expectedValue = "cab-location";
        assertEquals(expectedValue, AppConstant.CAB_LOCATION);
    }
}