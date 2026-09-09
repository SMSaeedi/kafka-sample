package com.example.demo.config;

import com.example.demo.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic cabLocationTopic() {
        return TopicBuilder
                .name(AppConstant.CAB_LOCATION)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic cabOrderTopic() {
        return TopicBuilder
                .name(AppConstant.CAB_ORDER)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic cabStatusTopic() {
        return TopicBuilder
                .name(AppConstant.CAB_STATUS)
                .partitions(3)
                .replicas(1)
                .build();
    }
}