package farid.guliev.simplerest.services;

import farid.guliev.simplerest.models.Measurement;
import farid.guliev.simplerest.repos.MeasurementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * A service class with the all 'business-logic' of working with data.
 */
public class MeasurementService {
    private final MeasurementRepo measurementRepo;

    @Autowired
    public MeasurementService(MeasurementRepo measurementRepo) {
        this.measurementRepo = measurementRepo;
    }

    public void save(Measurement measurement) {
        measurementRepo.save(measurement);
    }

    public List<Measurement> getAllMeasurements() {
        return measurementRepo.findAll();
    }

    public int getRainyDaysCount() {
        return (int) measurementRepo.findAll().stream().filter(Measurement::isRaining).count();
    }
}
