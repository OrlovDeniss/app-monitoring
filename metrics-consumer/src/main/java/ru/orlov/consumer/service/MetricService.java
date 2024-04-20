package ru.orlov.consumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.consumer.exception.MetricNotFoundException;
import ru.orlov.consumer.model.Metric;
import ru.orlov.consumer.model.MetricMapper;
import ru.orlov.consumer.repository.MetricRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MetricService {

    private static final MetricMapper metricMapper = MetricMapper.INSTANCE;

    private final MetricRepository metricRepository;

    public void saveMetric(MetricDto metricDto) {
        Metric newMetric = metricMapper.toEntity(metricDto);
        metricRepository.save(newMetric);
    }

    public Metric getMetricById(Long id) {
        return metricRepository.findById(id).orElseThrow(() -> new MetricNotFoundException("Метрика id = " + id + " не найдена"));
    }

    public List<Metric> getAllMetrics(Integer from, Integer size) {
        PageRequest pageRequest = PageRequest.of(from / size, size, Sort.by("id"));
        return metricRepository.findAll(pageRequest).toList();
    }
}
