package farid.guliev.simplerest.utils;

import farid.guliev.simplerest.dtos.MeasurementDTO;
import farid.guliev.simplerest.dtos.SensorDTO;
import farid.guliev.simplerest.models.Measurement;
import farid.guliev.simplerest.models.Sensor;
import farid.guliev.simplerest.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class DTOConverter {
    @Autowired
    private final SensorService sensorService;

    public DTOConverter(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * @param sensorDTO
     * @return A wrapped SensorDTO instance to the new Sensor instance.
     */
    public Sensor convertToSensor(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorDTO.getName());
        return sensor;
    }

    /**
     * @param measurementDTO
     * @return A wrapped MeasurementDTO instance to the new Measurement instance.
     */
    public Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        Measurement measurement = new Measurement();
        measurement.setRaining(measurementDTO.isRaining());
        measurement.setSensor(sensorService.findByName(measurementDTO.getSensorName()));
        measurement.setValue(measurementDTO.getValue());
        measurement.setMeasurementTime(LocalDateTime.now());
        return measurement;
    }

}
