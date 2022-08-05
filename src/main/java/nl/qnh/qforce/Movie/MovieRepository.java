package nl.qnh.qforce.Movie;

import nl.qnh.qforce.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<SWAPIMovie, Long> {

}
