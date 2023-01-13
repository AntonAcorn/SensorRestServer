package ru.acorn.SensorRestServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.SensorRestServer.model.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository <Measurement, Integer> {
}
