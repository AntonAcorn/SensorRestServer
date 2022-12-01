package ru.acorn.SensorRestServer.utils;

import lombok.Getter;
import lombok.Setter;

public class SensorErrorResponse {
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private long timestamp;

    public SensorErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
