package com.example.demo.service;

import com.example.demo.constant.AppConstant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.kafka.core.KafkaTemplate;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CabLocationServiceTest {
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private CabLocationService cabLocationService;

    @Test
    public void testUpdateCabLocation() {
        String mockCabLocation = "cab-location, cab-location-2";

        boolean result = cabLocationService.updateCabLocation(mockCabLocation);

        verify(kafkaTemplate, times(1)).send(AppConstant.CAB_LOCATION, mockCabLocation);

        assertTrue(result);
    }
}