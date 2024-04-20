package ru.orlov.consumer.util;

import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.consumer.model.Metric;

import java.util.List;

@UtilityClass
public class MetricTestUtils {

    private final EasyRandom easyRandom = new EasyRandom();

    public Metric createMetric() {
        return easyRandom.nextObject(Metric.class);
    }

    public List<Metric> createMetrics(int size) {
        return easyRandom.objects(Metric.class, size).toList();
    }

    public MetricDto createMetricDto() {
        return easyRandom.nextObject(MetricDto.class);
    }
}
