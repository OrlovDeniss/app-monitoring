package ru.orlov.consumer.exception;

public class MetricNotFoundException extends RuntimeException {

    public MetricNotFoundException(String message) {
        super(message);
    }
}
