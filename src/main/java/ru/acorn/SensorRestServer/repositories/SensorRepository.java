package ru.acorn.SensorRestServer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.SensorRestServer.modells.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository <Sensor, Integer> {
     Optional <Sensor> findSensorByName(String name);
     Optional <Sensor> deleteSensorByName(String name);
}
