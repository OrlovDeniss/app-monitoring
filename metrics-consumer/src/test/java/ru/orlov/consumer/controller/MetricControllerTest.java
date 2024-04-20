package ru.orlov.consumer.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.orlov.consumer.model.Metric;
import ru.orlov.consumer.service.MetricService;
import ru.orlov.consumer.util.MetricTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MetricController.class)
class MetricControllerTest {

    private static final String REQUEST_MAPPING = "/metrics";

    private final Random random = new Random();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MetricService metricService;

    @SneakyThrows
    @Test
    void getMetricByIdTest() {
        long id = random.nextLong(10) + 1L;
        Metric metric = MetricTestUtils.createMetric();

        when(metricService.getMetricById(anyLong()))
                .thenReturn(metric);

        mvc.perform(get(REQUEST_MAPPING + "/{id}", id)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(metric.getId()), Long.class))
                .andExpect(jsonPath("$.appMetric", is(metric.getAppMetric().getName())))
                .andExpect(jsonPath("$.value", is(metric.getValue())))
                .andExpect(jsonPath("$.createdAt", is(metric.getCreatedAt().toString())));

        verify(metricService, times(1))
                .getMetricById(anyLong());
    }

    @SneakyThrows
    @Test
    void getAllMetricsTest() {
        int size = random.nextInt(10) + 1;
        List<Metric> metrics = MetricTestUtils.createMetrics(size);

        when(metricService.getAllMetrics(anyInt(), anyInt()))
                .thenReturn(metrics);

        mvc.perform(get(REQUEST_MAPPING)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(metrics.size())));

        verify(metricService, times(1))
                .getAllMetrics(anyInt(), anyInt());
    }
}
