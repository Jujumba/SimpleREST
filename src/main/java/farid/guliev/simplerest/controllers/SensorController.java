package farid.guliev.simplerest.controllers;

import farid.guliev.simplerest.dtos.SensorDTO;
import farid.guliev.simplerest.errorResponses.SensorErrorResponse;
import farid.guliev.simplerest.exceptions.SensorAlreadyRegisteredException;
import farid.guliev.simplerest.models.Sensor;
import farid.guliev.simplerest.services.SensorService;
import farid.guliev.simplerest.utils.DTOConverter;
import farid.guliev.simplerest.validation.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/sensors")
/**
 * A controller class, that can only register sensors.
 */
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final DTOConverter dtoConverter;
    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, DTOConverter dtoConverter) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.dtoConverter = dtoConverter;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        @Valid
        Sensor sensor = dtoConverter.convertToSensor(sensorDTO);

        sensorValidator.validate(sensor,bindingResult);

        if (bindingResult.hasErrors())
            throw new SensorAlreadyRegisteredException(sensor.getName());

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyRegisteredException e) {
        SensorErrorResponse response = new SensorErrorResponse("This sensor is already registered!",
                LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
