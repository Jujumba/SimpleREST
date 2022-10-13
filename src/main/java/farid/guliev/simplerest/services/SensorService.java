package farid.guliev.simplerest.services;

import farid.guliev.simplerest.models.Sensor;
import farid.guliev.simplerest.repos.SensorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * A service class with the all 'business-logic' of working with data.
 */
public class SensorService {
    private final SensorRepo sensorRepo;

    @Autowired
    public SensorService(SensorRepo sensorRepo) {
        this.sensorRepo = sensorRepo;
    }

    public boolean contains(Sensor sensor) {
        return sensorRepo.findByName(sensor.getName()) != null;
    }

    public List<Sensor> getAllSensors() {
        return sensorRepo.findAll();
    }

    public Sensor findByName(String name) {
        return sensorRepo.findByName(name);
    }
    public void save(Sensor s) {
        sensorRepo.save(s);
    }
}
