package ru.orlov.consumer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.consumer.exception.MetricNotFoundException;
import ru.orlov.consumer.model.Metric;
import ru.orlov.consumer.repository.MetricRepository;
import ru.orlov.consumer.util.MetricTestUtils;

import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    private final Random random = new Random();

    @Mock
    private MetricRepository metricRepository;

    @InjectMocks
    private MetricService metricService;

    @Test
    void saveMetricTest() {
        MetricDto metricDto = MetricTestUtils.createMetricDto();
        Metric metric = MetricTestUtils.createMetric();

        when(metricRepository.save(any(Metric.class)))
                .thenReturn(metric);

        metricService.saveMetric(metricDto);

        verify(metricRepository, times(1))
                .save(any(Metric.class));
    }

    @Test
    void getMetricById() {
        long id = getRandomId();
        Metric metric = MetricTestUtils.createMetric();

        when(metricRepository.findById(anyLong()))
                .thenReturn(Optional.ofNullable(metric));

        Metric foundMetric = metricService.getMetricById(id);

        assertEquals(metric.getId(), foundMetric.getId());
        assertEquals(metric.getAppMetric(), foundMetric.getAppMetric());
        assertEquals(metric.getValue(), foundMetric.getValue());
        assertEquals(metric.getCreatedAt(), foundMetric.getCreatedAt());

        verify(metricRepository, times(1))
                .findById(anyLong());
    }

    @Test
    void getMetricByWrongId() {
        long id = getRandomId();

        when(metricRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(MetricNotFoundException.class,
                () -> metricService.getMetricById(id));

        verify(metricRepository, times(1))
                .findById(anyLong());
    }

    private long getRandomId() {
        return random.nextLong(10) + 1L;
    }
}
