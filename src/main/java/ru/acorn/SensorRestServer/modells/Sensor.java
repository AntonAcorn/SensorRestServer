package ru.acorn.SensorRestServer.modells;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table(name = "sensor")
@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    @Setter
    @Getter
    private Integer id;

    @Column(name = "name")
    @Getter
    @Setter
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 letters")
    private String name;

    public Sensor() {
    }
}
