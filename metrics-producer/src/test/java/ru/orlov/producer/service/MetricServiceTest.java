package ru.orlov.producer.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.producer.util.MetricTestUtils;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    @Value("${app.kafka.metric.topic.name}")
    private String TOPIC;

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @InjectMocks
    private MetricService metricService;

    @SneakyThrows
    @Test
    void saveMetricTest() {
        MetricDto metricDto = MetricTestUtils.createMetricDto();
        metricService.saveMetric(metricDto);

        verify(kafkaTemplate, times(1))
                .send(TOPIC, metricDto);
    }
}
