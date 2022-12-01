package ru.acorn.SensorRestServer.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class SensorDTO {
    @Getter
    @Setter
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 letters")
    private String name;

    public SensorDTO() {
    }
}
