package nl.qnh.qforce.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.qnh.qforce.resources.Configuration;
import nl.qnh.qforce.domain.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonServiceImpl personService;
    private final Configuration configuration;

    public PersonController(PersonServiceImpl personService, Configuration configuration) {
        this.personService = personService;
        this.configuration = configuration;
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
    public ResponseEntity<List<Person>> getPersons(@RequestParam(value = "name") String name) {
        String personsQuery = configuration.getBaseURL() + "people" + "?search=" + name;
        List<Person> personList = personService.search(personsQuery);
        if (personList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personList);
    }
}
