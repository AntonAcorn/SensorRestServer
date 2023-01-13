package ru.acorn.SensorRestServer.controllers;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.SensorRestServer.dto.SensorDTO;
import ru.acorn.SensorRestServer.model.Sensor;
import ru.acorn.SensorRestServer.service.SensorService;
import ru.acorn.SensorRestServer.utils.ErrorsUtil;
import ru.acorn.SensorRestServer.utils.SensorErrorResponse;
import ru.acorn.SensorRestServer.utils.SensorNotFoundException;
import ru.acorn.SensorRestServer.utils.SensorValidator;

@RestController
@RequestMapping("/sensors")
@Log4j
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
            log.debug(bindingResult);
        }
        sensorService.register(sensorToRegister);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/registration/{name}")
    public ResponseEntity<HttpStatus> unregister(@PathVariable String name){
        sensorService.unregister(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException (SensorNotFoundException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        log.error(e.getMessage());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertFromSensorDTOtoSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
    private SensorDTO convertFromSensorToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
