package ru.orlov.consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.orlov.appmonitoringmodel.MetricDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaListenerService {

    private final MetricService metricService;

    @KafkaListener(id = "metrics-listener", topics = "${app.kafka.metric.topic.name}")
    public void saveMetric(MetricDto metricDto) {
        metricService.saveMetric(metricDto);
    }
}
