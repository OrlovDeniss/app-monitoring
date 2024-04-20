package ru.orlov.producer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${app.kafka.metric.topic.name}")
    private String METRICS_TOPIC;

    @Bean
    public NewTopic metricsTopic() {
        return new NewTopic(METRICS_TOPIC, 1, (short) 1);
    }
}
