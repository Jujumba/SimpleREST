package farid.guliev.simplerest.exceptions;

/**
 * Throws when a measurement's sensor isn't registered in the database.
 */
public class SensorIsNotRegisteredException extends RuntimeException {
    public SensorIsNotRegisteredException() {

    }
    public SensorIsNotRegisteredException(String msg) {
        super(msg);
    }
}
