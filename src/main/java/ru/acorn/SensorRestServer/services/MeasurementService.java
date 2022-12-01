package ru.acorn.SensorRestServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.SensorRestServer.modells.Measurement;
import ru.acorn.SensorRestServer.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void saveMeasurement(Measurement measurement){
        enrich(measurement);
        measurementRepository.save(measurement);
    }

    public void enrich(Measurement measurement){
        measurement.setSensor(sensorService.getSensorByName(measurement.getSensor().getName()).get());
        measurement.setLocalDateTime(LocalDateTime.now());
    }

    public List<Measurement> getAllMeasurement (){
       return measurementRepository.findAll();
    }
}
