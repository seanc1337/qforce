package nl.qnh.qforce.Person;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.Movie.MovieMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonServiceImpl personService;
    private final String baseURL = "https://swapi.dev/api/";

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
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
    public ResponseEntity<List<SWAPIPerson>> getPersons() {
        List<SWAPIPerson> swapiPersons = this.personService.getPersons();

        return ResponseEntity.ok(swapiPersons);
    }
}
