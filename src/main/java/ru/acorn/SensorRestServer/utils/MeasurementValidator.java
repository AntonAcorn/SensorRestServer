package ru.acorn.SensorRestServer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.SensorRestServer.model.Measurement;
import ru.acorn.SensorRestServer.service.MeasurementService;
import ru.acorn.SensorRestServer.service.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private  final MeasurementService measurementService;
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if (measurement.getSensor() == null) return;
        if(sensorService.getSensorByName(measurement.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor", "", "There is no such sensor");
        }

    }
}
