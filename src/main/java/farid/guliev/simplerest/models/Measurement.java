package farid.guliev.simplerest.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
/**
 * A measurement class instance represents a weather measurement which was made by the weather 'sensor'.
 */
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    /**
     * Represents a weather 'condition' at the moment in the area, where the sensor is located.
     */
    private float value;
    @Column(name = "raining")
    /**
     * Represents a weather status in the time when measurement was made. If there was rainy or not.
     */
    private boolean isRaining;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    /**
     * A sensor that has made this measurement.
     */
    private Sensor sensor;
    @Column(name = "measurement_time")
    private LocalDateTime measurementTime;

    public Measurement(float value, boolean isRaining, Sensor sensor) {
        this.value = value;
        this.isRaining = isRaining;
        this.sensor = sensor;
    }

    public LocalDateTime getMeasurementTime() {
        return measurementTime;
    }

    public void setMeasurementTime(LocalDateTime measurementTime) {
        this.measurementTime = measurementTime;
    }

    public Measurement() {

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
