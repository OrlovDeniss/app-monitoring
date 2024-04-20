package ru.orlov.appmonitoringmodel;

import lombok.Getter;

import java.util.Arrays;

public enum AppMetric {

    CPU_USAGE("CPU_USAGE", "PERCENT"),
    MEMORY_USAGE("MEMORY_USAGE", "BYTES"),
    REQUESTS_PER_SECOND("REQUESTS_PER_SECOND", "REQUEST/SECOND"),
    ERROR_PER_SECOND("ERROR_PER_SECOND", "ERRORS/SECOND"),
    RESPONSE_TIME("RESPONSE_TIME", "MILLISECONDS"),
    DATABASE_CONNECTIONS("DATABASE_CONNECTIONS", "CONNECTIONS");

    @Getter
    private final String name;
    @Getter
    private final String unit;

    AppMetric(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public static AppMetric fromString(String string) {
        return Arrays.stream(values())
                .filter(appMetric -> appMetric.getName().equalsIgnoreCase(string))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(string));
    }
}
