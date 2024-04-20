package ru.orlov.producer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.producer.service.MetricService;
import ru.orlov.producer.util.MetricTestUtils;

import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MetricController.class)
class MetricControllerTest {

    private static final String REQUEST_MAPPING = "/metrics";

    @MockBean
    private MetricService metricService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @Test
    void saveNewMetricTest() {
        MetricDto newMetricDto = MetricTestUtils.createMetricDto();

        mvc.perform(post(REQUEST_MAPPING)
                        .content(objectMapper.writeValueAsString(newMetricDto))
                        .characterEncoding(StandardCharsets.UTF_8)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(metricService, times(1))
                .saveMetric(newMetricDto);
    }
}
