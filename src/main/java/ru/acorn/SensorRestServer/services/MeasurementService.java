package ru.acorn.SensorRestServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.SensorRestServer.modells.Measurement;
import ru.acorn.SensorRestServer.repositories.MeasurementRepository;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }
    @Transactional
    public void saveMeasurement(Measurement measurement){
        measurementRepository.save(measurement);
    }
}
