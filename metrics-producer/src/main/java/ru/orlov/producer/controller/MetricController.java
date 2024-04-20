package ru.orlov.producer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.producer.service.MetricService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("metrics")
public class MetricController {

    private final MetricService metricService;

    @Operation(summary = "Создать новую метрику")
    @ApiResponse(responseCode = "200", description = "Создание новой метрики",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = MetricDto.class))})
    @PostMapping
    public ResponseEntity<Void> saveMetric(@RequestBody @Valid MetricDto metric) {
        log.info("POST metrics");
        metricService.saveMetric(metric);
        return ResponseEntity.ok().build();
    }
}
