package farid.guliev.simplerest.controllers;

import dev.jujumba.project3.dtos.MeasurementDTO;
import dev.jujumba.project3.errorResponses.SensorErrorResponse;
import dev.jujumba.project3.exceptions.SensorIsNotRegisteredException;
import dev.jujumba.project3.models.Measurement;
import dev.jujumba.project3.services.MeasurementService;
import dev.jujumba.project3.services.SensorService;
import dev.jujumba.project3.utils.DTOConverter;
import dev.jujumba.project3.validation.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/measurements")
/**
 * A controller class, that can accept and return the measurements
 */
public class MeasurementController {
    private final SensorService sensorService;
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final DTOConverter dtoConverter;

    @Autowired
    public MeasurementController(SensorService sensorService, MeasurementService measurementService, MeasurementValidator measurementValidator, DTOConverter dtoConverter) {
        this.sensorService = sensorService;
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.dtoConverter = dtoConverter;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        @Valid
        Measurement measurement = dtoConverter.convertToMeasurement(measurementDTO);
        measurementValidator.validate(measurement,bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!sensorService.contains(measurement.getSensor())) {
            throw new SensorIsNotRegisteredException("This is sensor is not registered yet!");
        }
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Measurement>> getMeasurements() {
        return new ResponseEntity<>(measurementService.getAllMeasurements(),HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Integer> getRainyDaysCount() {
        return new ResponseEntity<>(measurementService.getRainyDaysCount(),HttpStatus.OK);
    }
    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorIsNotRegisteredException e) {
        SensorErrorResponse response = new SensorErrorResponse("This sensor isn't registered!",
                LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
