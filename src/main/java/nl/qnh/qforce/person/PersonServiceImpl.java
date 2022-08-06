package nl.qnh.qforce.person;

//import nl.qnh.qforce.Movie.MovieRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.movie.MovieMapper;
import nl.qnh.qforce.response.ResponseMapper;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.response.SWAPIResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PersonMapper personMapper = new PersonMapper(objectMapper);
    private final ResponseMapper responseMapper = new ResponseMapper(objectMapper);
    private final MovieMapper movieMapper = new MovieMapper(objectMapper);

    public PersonServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<Person> search(String query) {
        String personResult = restTemplate.getForObject(query, String.class);
        SWAPIResponse response = responseMapper.mapToSWAPIResponse(personResult);
        List<Person> persons = personMapper.mapToPersonModel(response);

        return persons;
    }

    @Override
    public Optional<Person> get(long id) {
        return Optional.empty();
    }

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

