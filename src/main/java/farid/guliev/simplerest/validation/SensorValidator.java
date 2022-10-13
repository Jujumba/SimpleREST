package farid.guliev.simplerest.validation;

import farid.guliev.simplerest.models.Sensor;
import farid.guliev.simplerest.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class);
    }

    @Override
    /**
     * The sensor won't pass validation if it already registered.
     */
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.contains(sensor))
            errors.rejectValue("sensor","",
                    "This sensor already registered.");
    }
}
