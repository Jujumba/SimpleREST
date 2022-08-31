package farid.guliev.simplerest.exceptions;

/**
 * Throws when a measurement's value isn't between -100 and 100.
 */
public class BadMeasurementValueException extends RuntimeException{
    public BadMeasurementValueException() {

    }

    public BadMeasurementValueException(String msg) {
        super(msg);
    }
}
