package farid.guliev.simplerest.controllers;

import dev.jujumba.project3.dtos.SensorDTO;
import dev.jujumba.project3.errorResponses.SensorErrorResponse;
import dev.jujumba.project3.exceptions.SensorAlreadyRegisteredException;
import dev.jujumba.project3.models.Sensor;
import dev.jujumba.project3.services.SensorService;
import dev.jujumba.project3.utils.DTOConverter;
import dev.jujumba.project3.validation.SensorValidator;
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
