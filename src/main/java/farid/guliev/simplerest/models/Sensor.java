package farid.guliev.simplerest.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sensor")
/**
 * A sensor class instance represents a real meteorological sensor, that can make measurements and send
 * them to the server. But before it, sensor is need to be registered.
 */
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @Size(min = 3, max = 30, message = "Sensor's name should be between 3 and 30 characters")
    private String name;
    @OneToMany(mappedBy = "sensor")
    @JsonIgnore
    private List<Measurement> measurements;

    public Sensor(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public Sensor(String name) {
        this.name = name;
    }
    public Sensor() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
