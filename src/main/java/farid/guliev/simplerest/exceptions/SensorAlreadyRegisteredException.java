package farid.guliev.simplerest.exceptions;

/**
 * Throws when a measurement's sensor already registered in the database.
 */
public class SensorAlreadyRegisteredException extends RuntimeException {
    public SensorAlreadyRegisteredException() {

    }

    public SensorAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
