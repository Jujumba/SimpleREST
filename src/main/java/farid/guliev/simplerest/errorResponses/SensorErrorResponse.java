package farid.guliev.simplerest.errorResponses;

import java.time.LocalTime;

/**
 * Represents a response object, that will be returned when the exception will occur.
 */
public class SensorErrorResponse {
    private String message;
    private LocalTime now;

    public SensorErrorResponse(String message, LocalTime now) {
        this.message = message;
        this.now = now;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getNow() {
        return now;
    }

    public void setNow(LocalTime now) {
        this.now = now;
    }
}
