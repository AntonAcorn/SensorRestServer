package ru.acorn.SensorRestServer.controllers;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.SensorRestServer.dto.MeasurementDTO;
import ru.acorn.SensorRestServer.model.Measurement;
import ru.acorn.SensorRestServer.service.MeasurementService;
import ru.acorn.SensorRestServer.utils.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("measurements")
@Log4j
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurements (@RequestBody @Valid MeasurementDTO measurementDTO,
                                                       BindingResult bindingResult){
        Measurement measurementToAdd = convertFromMeasurementDTOToMeasurement(measurementDTO);
        measurementValidator.validate(measurementToAdd, bindingResult);
        if (bindingResult.hasErrors()){
            ErrorsUtil.returnErrorMessage(bindingResult);
            log.debug(bindingResult);
        }
        measurementService.saveMeasurement(measurementToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public MeasurementResponse getMeasurements (){
        return new MeasurementResponse(measurementService.getAllMeasurement()
                .stream()
                .map(this::convertFromMeasurementToMeasurementDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public Long getRainyDays(){
        log.info("Counting rainy days");
        return measurementService.getAllMeasurement().stream().filter(Measurement::isRaining).count();
    }

    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException (SensorNotFoundException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        log.error(e.getMessage());
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }
    private MeasurementDTO convertFromMeasurementToMeasurementDto(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
    private Measurement convertFromMeasurementDTOToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
