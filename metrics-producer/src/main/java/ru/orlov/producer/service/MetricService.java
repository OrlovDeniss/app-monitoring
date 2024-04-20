package ru.orlov.producer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.orlov.appmonitoringmodel.MetricDto;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${app.kafka.metric.topic.name}")
    private String METRICS_TOPIC;

    public void saveMetric(MetricDto metricDto) {
        kafkaTemplate.send(METRICS_TOPIC, metricDto);
    }
}
