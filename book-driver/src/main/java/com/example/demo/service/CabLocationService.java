package com.example.demo.service;

import com.example.demo.constant.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CabLocationService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public boolean updateCabLocation(String cabLocation) {
        kafkaTemplate.send(AppConstant.CAB_LOCATION, cabLocation);
        return true;
    }
}
