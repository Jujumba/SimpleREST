package farid.guliev.simplerest.validation;

import farid.guliev.simplerest.models.Measurement;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Measurement.class);
    }

    @Override
    /**
     * The measurement won't pass a validation if it's value isn't between -100.0 and 100.0.
     */
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;
        if(measurement.getValue() < -100 || measurement.getValue() > 100)
            errors.rejectValue("value","","Incorrect value");
    }
}
