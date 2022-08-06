package nl.qnh.qforce.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.movie.MovieMapper;
import nl.qnh.qforce.response.SWAPIResponse;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {
    private final ObjectMapper objectMapper;
    private RestTemplate restTemplate = new RestTemplate();

    public PersonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Person> mapToPersonModel(SWAPIResponse swapiResponse) {
        MovieMapper movieMapper = new MovieMapper(objectMapper);
        List<Person> persons = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();

        for (SWAPIPerson swapiPerson : swapiResponse.getResults()) {
            PersonModel personModel = new PersonModel();
            personModel.setName(swapiPerson.getName());
            personModel.setGender(Gender.valueOf(swapiPerson.getGender().toUpperCase()));
            personModel.setHeight(Integer.parseInt(swapiPerson.getHeight()));
            personModel.setWeight(Integer.valueOf(swapiPerson.getMass()));
            personModel.setBirthYear(swapiPerson.getBirthYear());
            personModel.setId(Long.parseLong("1"));

//            for (String film : swapiPerson.getFilms()) {
//                SWAPIMovie swapiMovie = restTemplate.getForObject(film, SWAPIMovie.class);
//                if(swapiMovie != null) {
//                    MovieModel movieModel = movieMapper.mapToMovieModel(swapiMovie);
//                    movies.add(movieModel);
//                }
//            }
//            personModel.setMovies(movies);
            persons.add(personModel);
        }
        return persons;
    }
}
