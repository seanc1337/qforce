package nl.qnh.qforce.Repository;

import nl.qnh.qforce.SWAPIObject.SWAPIMovie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<SWAPIMovie, Long> {
}
