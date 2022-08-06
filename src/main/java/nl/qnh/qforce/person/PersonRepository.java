package nl.qnh.qforce.person;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<SWAPIPerson, Long>{

}
