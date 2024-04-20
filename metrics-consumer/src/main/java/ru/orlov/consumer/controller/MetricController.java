package ru.orlov.consumer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.orlov.consumer.model.Metric;
import ru.orlov.consumer.service.MetricService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("metrics")
public class MetricController {

    private static final String FROM = "0";
    private static final String SIZE = "10";

    private final MetricService metricService;

    @Operation(summary = "Получить метрику по id")
    @ApiResponse(responseCode = "200", description = "Получение метрик по id",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Metric.class))})
    @GetMapping("{id}")
    public ResponseEntity<Metric> getMetricById(@PathVariable @Positive Long id) {
        log.info("GET metrics/{}", id);
        return ResponseEntity.ok(metricService.getMetricById(id));
    }

    @Operation(summary = "Получить все метрики")
    @ApiResponse(responseCode = "200", description = "Получение всех метрик",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Metric.class))))
    @GetMapping
    public ResponseEntity<List<Metric>> getAllMetrics(@RequestParam(required = false, defaultValue = FROM) @PositiveOrZero Integer from,
                                                      @RequestParam(required = false, defaultValue = SIZE) @Positive Integer size) {
        log.info("GET metrics");
        return ResponseEntity.ok(metricService.getAllMetrics(from, size));
    }
}
