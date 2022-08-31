package farid.guliev.simplerest.repos;

import dev.jujumba.project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * A repo, that works with the database.
 */
public interface SensorRepo extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String name);
}
