package ru.acorn.SensorRestServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.SensorRestServer.model.Sensor;
import ru.acorn.SensorRestServer.repository.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public void register (Sensor sensor){
        sensorRepository.save(sensor);
    }
    @Transactional
    public void unregister (String name){
        sensorRepository.deleteSensorByName(name);
    }

    public Optional<Sensor> getSensorByName(String name){
       return sensorRepository.findSensorByName(name);
    }
}
