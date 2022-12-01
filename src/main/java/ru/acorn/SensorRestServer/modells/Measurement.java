package ru.acorn.SensorRestServer.modells;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Column (name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be greater than -100")
    @Max(value = 100, message = "Value should be less than 100")
    @Getter
    @Setter
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "Raining should be true or false")
    @Getter
    @Setter
    private boolean raining;

    @Column(name = "measurement_date_time")
    @NotNull
    @Getter
    @Setter
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    @NotNull
    @Getter
    @Setter
    private Sensor sensor;

    public Measurement() {
    }
}
