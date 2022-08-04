package nl.qnh.qforce.Repository;

import nl.qnh.qforce.SWAPIObject.SWAPIPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<SWAPIPerson, Long>{

}
