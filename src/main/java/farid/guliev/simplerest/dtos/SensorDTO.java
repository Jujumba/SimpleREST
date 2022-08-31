package farid.guliev.simplerest.dtos;

import javax.validation.constraints.NotEmpty;

/**
 * A SensorDTO class instance represents a wrapper to the Sensor class instance.
 */
public class SensorDTO {
    @NotEmpty
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
