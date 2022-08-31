package farid.guliev.simplerest.dtos;

/**
 * A MeasurementDTO class instance represents a wrapper to the Measurement class instance.
 */
public class MeasurementDTO {
    private float value;
    private boolean isRaining;
    private String sensorName;

    public MeasurementDTO(float value, boolean isRaining, String sensorName) {
        this.value = value;
        this.isRaining = isRaining;
        this.sensorName = sensorName;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
