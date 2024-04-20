package ru.orlov.appmonitoringmodel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetricDto {

    @NotNull
    private AppMetric appMetric;

    @NotNull
    private Double value;
}
