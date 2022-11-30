package ru.acorn.SensorRestServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository <MeasurementRepository, Integer> {
}
