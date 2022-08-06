package nl.qnh.qforce.person;

import nl.qnh.qforce.resources.Configuration;
import nl.qnh.qforce.domain.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final PersonServiceImpl personService;
    private final Configuration configuration;

    public PersonController(PersonServiceImpl personService, Configuration configuration) {
        this.personService = personService;
        this.configuration = configuration;
    }

//    /**
//     * Get Person by ID
//     * @param id the id to search for
//     * @return the Person found
//     */
//    @GetMapping(value = "/persons/{id}")
//    @ResponseBody
//    public ResponseEntity<Person> getPersonsById(@PathVariable String id) {
//        Optional<Person> person = personService.get(Long.parseLong(id));
//        if (person.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(person.get());
//    }

    /**
     * Searches for persons
     *
     * @return The list of persons
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(value = "name") String name) {
        String personQuery = configuration.getBaseURL() + "people" + "?search=" + name;
        List<Person> personList = personService.search(personQuery);

        return ResponseEntity.ok(personList);
    }
}
