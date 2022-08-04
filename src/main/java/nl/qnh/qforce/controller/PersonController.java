package nl.qnh.qforce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.SWAPIObject.SWAPIPerson;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.mapper.MovieMapper;
import nl.qnh.qforce.mapper.PersonMapper;
import nl.qnh.qforce.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonServiceImpl personService;
    private final String baseURL = "https://swapi.dev/api/";
    private ObjectMapper mapper = new ObjectMapper();
    PersonMapper personMapper = new PersonMapper(mapper);
    MovieMapper movieMapper = new MovieMapper(mapper);
    RestTemplate restTemplate = new RestTemplate();

    /**
     * Method for getting Persons from SWAPI and saving them to the in memory DB to avoid making a manual call through
     * it's own endpoint
     */
    @PostConstruct
    public void getAndSavePersonsToDb() {
        List<SWAPIPerson> swapiPersons;

        String url = this.baseURL + "people/";

        String personResult = this.restTemplate.getForObject(url, String.class);

        if(personResult != null) {
            String personResultCorrected = replaceUnknownValues(personResult);

            swapiPersons = personMapper.fromJsonToSWAPIPerson(personResultCorrected);

            for (SWAPIPerson swapiPerson : swapiPersons) {
                movieMapper.getFilmsFromSWAPI(swapiPerson, restTemplate);

                this.personService.saveMovies(swapiPerson.getMovies());
            }

            this.personService.savePersons(swapiPersons);
        }
    }

    /**
     * Get Person by ID
     * @param id the id to search for
     * @return the Person found
     */
    @GetMapping(value = "/persons/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Person>> getPersonsById(@PathVariable String id) {
        Optional<Person> swapiPerson;
        try {
            swapiPerson = this.personService.get(Long.parseLong(id));

            return new ResponseEntity<>(swapiPerson, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Searches for persons
     * @return The list of persons
     */
    @GetMapping(value = "/persons")
    public ResponseEntity<List<Person>> getPersons(@RequestParam String q) {
        try {
            List<Person> swapiPersons = this.personService.search("");

            return new ResponseEntity<>(swapiPersons, HttpStatus.OK);
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException && ((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Replacing values not used: unknown, N/A, hermaphrodite
     * @param result The String result from the API call
     * @return The modified String
     */
    public String replaceUnknownValues(String result) {
        if (result.contains("\"gender\":\"n/a\"")) {
            result = result.replace("\"gender\":\"n/a\"", "\"gender\":\"unknown\"");
        } if (result.contains("\"gender\":\"hermaphrodite\"") || result.contains("\"gender\":\"none\"")) {
            result = result.replace("\"gender\":\"hermaphrodite\"", "\"gender\":\"not_applicable\"");
            result = result.replace("\"gender\":\"none\"", "\"gender\":\"not_applicable\"");
        } if (result.contains("\"mass\":\"unknown\"")) {
            result = result.replace("\"mass\":\"unknown\"", "\"mass\":\"0\"");
        } if (result.contains("\"height\":\"unknown\"")) {
            result = result.replace("\"height\":\"unknown\"", "\"height\":\"0\"");
        }

        return result;
    }
}
