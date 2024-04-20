# App monitoring
Система мониторинга

## Стэк
Java 17, Maven, Spring Boot 3, Spring Data, PostgreSQl, Kafka, JUnit, Mockito, TestContainers

## Структура
![](Structure.jpg)

## API

### 1. metrics-producer service

URL: http://localhost:8080

- POST /metrics - создать метрику

Подробнее: http://localhost:8080/swagger-ui/index.html

### 2. metrics-consumer service

URL: http://localhost:8090

- GET /metrics/{id} - получить метрику по id
- GET /metrics - получить список всех метрик

Подробнее: http://localhost:8090/swagger-ui/index.html

## Зависимости

- Zookeeper: http://localhost:2181

- Kafka: http://localhost:9092


## Сборка и запуск
0. Используйте docker-compose файл для запуска зависимостей Zookeeper и Kafka
```Bash
docker-compose up
```
1. Скопируйте репозиторий:
```Bash
git clone https://github.com/OrlovDeniss/app-monitoring.git
```
2. Перейдите в папку metrics-producer:
```Bash
cd app-monitoring/metrics-producer
```
3. Скомпилируйте исходные файлы producer-service:
```Bash
mvn clean package
```
4. Запустите metrics-producer из папки target:
```Bash
java -jar metrics-producer-0.0.1-SNAPSHOT.jar
```
5. Повторите пункты 2-4 для metrics-consumer.