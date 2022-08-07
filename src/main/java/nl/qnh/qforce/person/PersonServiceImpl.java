package nl.qnh.qforce.person;

//import nl.qnh.qforce.Movie.MovieRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
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
        while(response.getNext() != null) {
            String nextResult = restTemplate.getForObject(response.getNext(), String.class);
            SWAPIResponse nextResponse = responseMapper.mapToSWAPIResponse(nextResult);
            persons.addAll(personMapper.mapToPersonModel(nextResponse));
            response = nextResponse;
        }

        return persons;
    }

    @Override
    public Optional<Person> get(long id) {
        String query = "https://swapi.dev/api/people/" + id;
        String personResult = restTemplate.getForObject(query, String.class);
        Optional<Person> newPerson = Optional.empty();
        try {
            Person person = personMapper.mapToPersonModel(personResult);
            newPerson = Optional.of(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return newPerson;
    }
}

