package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The Controller for usage of endpoints
 * @author Sean
 */
@RestController
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    /**
     * Get Person by ID
     * @param id the id to search for
     * @return the Person found
     */
    @GetMapping(value = "/persons/{id}")
    @ResponseBody
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        Optional<Person> person = personService.get(Long.parseLong(id));
        if (person.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(person.get());
    }

    /**
     * Searches for persons
     *
     * @return The list of persons
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getPersons(@RequestParam(value = "q") String name) {
        List<Person> personList = personService.search(name);
        if (personList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personList);
    }
}
