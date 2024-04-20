package ru.orlov.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.orlov.consumer.model.Metric;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
