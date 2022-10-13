package farid.guliev.simplerest.repos;

import farid.guliev.simplerest.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
/**
 * A repo, that works with the database.
 */
public interface MeasurementRepo extends JpaRepository<Measurement, Integer> {

}
