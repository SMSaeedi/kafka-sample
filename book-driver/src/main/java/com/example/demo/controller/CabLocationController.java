package com.example.demo.controller;

import com.example.demo.service.CabLocationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cab-locations")
public class CabLocationController {
    private final CabLocationService cabLocationService;

    @SneakyThrows
    @PutMapping("/update")
    public ResponseEntity updateLocation() {
        int range = 100;
        while (range > 0) {
            cabLocationService.updateCabLocation(Math.random() + " " + Math.random());
            Thread.sleep(1000);
            range--;
        }
        return new ResponseEntity(Map.of("message", "Location is Updated"), HttpStatus.OK);
    }
}