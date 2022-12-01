package ru.acorn.SensorRestServer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.SensorRestServer.modells.Measurement;
import ru.acorn.SensorRestServer.services.MeasurementService;

@Component
public class MeasurementValidator implements Validator {
    private  final MeasurementService measurementService;
    @Autowired
    public MeasurementValidator(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
