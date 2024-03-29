package ru.acorn.SensorRestServer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.acorn.SensorRestServer.model.Sensor;

public class MeasurementDTO {
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    @Getter
    @Setter
    private Double value;

    @NotNull(message = "Raining should be true or false")
    @Getter
    @Setter
    private boolean raining;

    @NotNull
    @Getter
    @Setter
    private Sensor sensor;

    public MeasurementDTO() {
    }
}
