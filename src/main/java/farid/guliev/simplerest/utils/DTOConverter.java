package farid.guliev.simplerest.utils;

import dev.jujumba.project3.dtos.MeasurementDTO;
import dev.jujumba.project3.dtos.SensorDTO;
import dev.jujumba.project3.models.Measurement;
import dev.jujumba.project3.models.Sensor;
import dev.jujumba.project3.services.SensorService;
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
