package ru.orlov.consumer.service;

import lombok.SneakyThrows;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.orlov.appmonitoringmodel.MetricDto;
import ru.orlov.consumer.util.MetricTestUtils;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@TestPropertySource(properties = {"spring.kafka.consumer.auto-offset-reset=earliest"})
@Testcontainers
class KafkaListenerServiceTest {

    @Value("${app.kafka.metric.topic.name}")
    private String TOPIC;

    @ClassRule
    public static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"));

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        kafka.start();
        registry.add("spring.kafka.bootstrap-servers", () -> kafka.getBootstrapServers());
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @MockBean
    private MetricService metricService;

    @InjectMocks
    private KafkaListenerService kafkaListenerService;

    @SneakyThrows
    @Test
    void getMetricTest() {
        MetricDto metricDto = MetricTestUtils.createMetricDto();
        kafkaTemplate.send(TOPIC, metricDto);

        await()
                .pollInterval(Duration.ofSeconds(3))
                .atMost(10, SECONDS)
                .untilAsserted(() -> verify(metricService, times(1)).saveMetric(metricDto));
    }
}
