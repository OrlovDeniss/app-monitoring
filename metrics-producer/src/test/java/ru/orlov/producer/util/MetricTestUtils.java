package ru.orlov.producer.util;

import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;
import ru.orlov.appmonitoringmodel.MetricDto;

@UtilityClass
public class MetricTestUtils {

    private final EasyRandom easyRandom = new EasyRandom();

    public MetricDto createMetricDto() {
        return easyRandom.nextObject(MetricDto.class);
    }
}
