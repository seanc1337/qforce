package nl.qnh.qforce.Person;

import nl.qnh.qforce.Resources.Configuration;
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
    public ResponseEntity<List<Person>> getPersons(@RequestParam String name) {
        String query = configuration.getBaseURL() + "people" + "?name=" + name;
        List<Person> swapiPersonList = personService.search(query);

        return null;
    }
}
