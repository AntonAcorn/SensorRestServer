package ru.acorn.SensorRestServer.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.acorn.SensorRestServer.services.dto.MeasurementDTO;
import ru.acorn.SensorRestServer.modells.Measurement;
import ru.acorn.SensorRestServer.services.MeasurementService;
import ru.acorn.SensorRestServer.services.dto.SensorDTO;
import ru.acorn.SensorRestServer.utils.MeasurementValidator;

@Controller
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> addMeasurements (@RequestBody @Valid MeasurementDTO measurementDTO,
                                                       BindingResult bindingResult){

        return ResponseEntity.ok(HttpStatus.OK);

    }
    private SensorDTO convertFromMeasurementToMeasurementDto(Measurement measurement){
        return m
    }
}
