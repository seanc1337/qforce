package nl.qnh.qforce.movie;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<SWAPIMovie, Long> {

}
