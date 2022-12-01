package ru.acorn.SensorRestServer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.acorn.SensorRestServer.dto.SensorDTO;
import ru.acorn.SensorRestServer.modells.Sensor;
import ru.acorn.SensorRestServer.services.SensorService;
import ru.acorn.SensorRestServer.utils.ErrorsUtil;
import ru.acorn.SensorRestServer.utils.SensorErrorResponse;
import ru.acorn.SensorRestServer.utils.SensorNotFoundException;
import ru.acorn.SensorRestServer.utils.SensorValidator;

import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }


    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration (@RequestBody @Valid SensorDTO sensorDTO,
                                                    BindingResult bindingResult){
        Sensor sensorToRegister = convertFromSensorDTOtoSensor(sensorDTO);
        sensorValidator.validate(sensorToRegister, bindingResult);
        if(bindingResult.hasErrors()){
            ErrorsUtil.returnErrorMessage(bindingResult);
        }
        sensorService.register(sensorToRegister);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/registration/{name}")
    public ResponseEntity<HttpStatus> unregister(@PathVariable String name){
        sensorService.unregister(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler//это будет отображаться в постмане
    private ResponseEntity<SensorErrorResponse> handleException (SensorNotFoundException e){//берет из валидатора или аннотаций
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertFromSensorDTOtoSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    private SensorDTO convertFromSensorToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
