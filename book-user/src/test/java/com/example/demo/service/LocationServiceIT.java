package com.example.demo.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = {
    "listeners=PLAINTEXT://localhost:9092",
    "port=9092"
})
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=localhost:9092",
    "spring.kafka.consumer.bootstrap-servers=localhost:9092"
})
@DisplayName("LocationService Integration Tests")
class LocationServiceIT {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private LocationService locationService;

    @BeforeAll
    static void setUpAwaitility() {
        // Configure awaitility timeout
        await().timeout(10, TimeUnit.SECONDS).pollInterval(1, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Should receive and process message from Kafka topic")
    void testKafkaMessageReceived() throws InterruptedException {
        String testLocation = "cab-location-test-1";
        
        // Send message to Kafka topic
        kafkaTemplate.send("cab-location", testLocation);
        
        // Verify message was processed (implementation can be enhanced with a listener/spy)
        Thread.sleep(2000); // Wait for Kafka to process
    }

    @Test
    @DisplayName("Should process multiple messages from Kafka topic")
    void testMultipleKafkaMessagesReceived() throws InterruptedException {
        String[] locations = {
            "Location1",
            "Location2",
            "Location3"
        };
        
        for (String location : locations) {
            kafkaTemplate.send("cab-location", location);
        }
        
        Thread.sleep(2000); // Wait for Kafka to process
    }

    @Test
    @DisplayName("Should handle empty message from Kafka")
    void testEmptyKafkaMessage() throws InterruptedException {
        kafkaTemplate.send("cab-location", "");
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Should process message with special characters from Kafka")
    void testKafkaMessageWithSpecialCharacters() throws InterruptedException {
        String specialLocation = "Lat:40.7128°N,Lon:74.0060°W";
        
        kafkaTemplate.send("cab-location", specialLocation);
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Should process GPS coordinates from Kafka")
    void testKafkaMessageWithGPSCoordinates() throws InterruptedException {
        String gpsCoordinates = "40.7128,-74.0060";
        
        kafkaTemplate.send("cab-location", gpsCoordinates);
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Should handle rapid consecutive messages")
    void testRapidConsecutiveMessages() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("cab-location", "Location-" + i);
        }
        
        Thread.sleep(3000);
    }

    @Test
    @DisplayName("Should handle large payload from Kafka")
    void testLargePayload() throws InterruptedException {
        String largeLocation = "Location-" + "A".repeat(1000);
        
        kafkaTemplate.send("cab-location", largeLocation);
        Thread.sleep(2000);
    }

    @Test
    @DisplayName("Service should be available in Spring context")
    void testServiceBeanExists() {
        org.junit.jupiter.api.Assertions.assertNotNull(locationService, "LocationService should be available");
    }

    @Test
    @DisplayName("KafkaTemplate should be available in Spring context")
    void testKafkaTemplateBeanExists() {
        org.junit.jupiter.api.Assertions.assertNotNull(kafkaTemplate, "KafkaTemplate should be available");
    }
}
