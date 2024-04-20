package ru.orlov.consumer.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.orlov.appmonitoringmodel.MetricDto;

@Mapper
public interface MetricMapper {

    MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);

    Metric toEntity(MetricDto metricDto);
}
