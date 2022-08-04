package nl.qnh.qforce.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.SWAPIObject.SWAPIMovie;
import nl.qnh.qforce.SWAPIObject.SWAPIPerson;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {
    public ObjectMapper objectMapper;

    public MovieMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void getFilmsFromSWAPI(Person swapiPerson, RestTemplate restTemplate) {
        List<Movie> movies = new ArrayList<>();
        for (String film : ((SWAPIPerson) swapiPerson).films) {
            SWAPIMovie filmResult = restTemplate.getForObject(film, SWAPIMovie.class);
            movies.add(filmResult);
        }
        ((SWAPIPerson) swapiPerson).movies = movies;
    }
}
