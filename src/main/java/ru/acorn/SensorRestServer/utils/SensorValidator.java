package ru.acorn.SensorRestServer.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.SensorRestServer.model.Sensor;
import ru.acorn.SensorRestServer.service.SensorService;
@Component
public class SensorValidator  implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.getSensorByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", "null", "This sensor is already exists");
        }
    }
}
