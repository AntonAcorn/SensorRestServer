package ru.acorn.SensorRestServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementService(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }
}
