package nl.qnh.qforce.Person;

//import nl.qnh.qforce.Movie.MovieRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.Resources.StarWarsApi;
import nl.qnh.qforce.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonServiceImpl {


//    private final PersonRepository personRepository;
//
//    private final MovieRepository movieRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PersonMapper personMapper = new PersonMapper(objectMapper);

    public List<SWAPIPerson> getPersons() {
        List<SWAPIPerson> swapiPersons;

        String url = "https://swapi.dev/api/people";

        RestTemplate restTemplate = new RestTemplate();

        String personResult = restTemplate.getForObject(url, String.class);

        swapiPersons = personMapper.fromJsonToSWAPIPerson(personResult);

        return swapiPersons;
    }

//    /**
//     * Method for getting Persons from SWAPI and saving them to the in memory DB to avoid making a manual call through
//     * it's own endpoint
//     */
//    @PostConstruct
//    public void getAndSavePersonsToDb() {
//        List<SWAPIPerson> swapiPersons;
//
//        String url = this.baseURL + "people/";
//
//        String personResult = this.restTemplate.getForObject(url, String.class);
//
//        if(personResult != null) {
//            String personResultCorrected = replaceUnknownValues(personResult);
//
//            swapiPersons = personMapper.fromJsonToSWAPIPerson(personResultCorrected);
//
//            for (SWAPIPerson swapiPerson : swapiPersons) {
//                movieMapper.getFilmsFromSWAPI(swapiPerson, restTemplate);
//
//                this.personService.saveMovies(swapiPerson.getMovies());
//            }
//
//            this.personService.savePersons(swapiPersons);
//        }
//    }

//    /**
//     * Save list of persons to DB
//     * @param persons List of persons
//     * @return The list of persons
//     */
//    public List<SWAPIPerson> savePersons(@RequestBody List<SWAPIPerson> persons) {
//
//        personRepository.saveAll(persons);
//
//        return persons;
//    }

//    /**
//     * Save the list of movies found per Person
//     * @param movies List of movies per person
//     * @return The list of movies
//     */
//    public List<Movie> saveMovies(@RequestBody List<Movie> movies) {
//        movieRepository.saveAll(movies);
//
//        return movies;
//    }
}
