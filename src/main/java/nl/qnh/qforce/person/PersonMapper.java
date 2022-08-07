package nl.qnh.qforce.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.domain.*;
import nl.qnh.qforce.movie.MovieMapper;
import nl.qnh.qforce.movie.SWAPIMovie;
import nl.qnh.qforce.response.SWAPIResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Mapper class for mapping SWAPIPersons to PersonModels
 * @author Sean
 */
@Service
public class PersonMapper {
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final Map<String, Integer> physiqueMap = new HashMap<>();
    private final Map<String, Gender> genderMap = new HashMap<>();

    public PersonMapper(ObjectMapper objectMapper, final RestTemplate restTemplate) {
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
        physiqueMap.put("unknown", 0);
        genderMap.put("hermaphrodite", Gender.NOT_APPLICABLE);
        genderMap.put("n/a", Gender.UNKNOWN);
        genderMap.put("none", Gender.NOT_APPLICABLE);
    }

    /**
     * Returns a combined List(if multiple next pages) of persons
     * @param swapiResponse The response from the Star Wars API
     * @return List of people found
     */
    public List<Person> mapToPersonModel(SWAPIResponse swapiResponse) {
        MovieMapper movieMapper = new MovieMapper(objectMapper);
        List<Person> persons = new ArrayList<>();

        for (SWAPIPerson swapiPerson : swapiResponse.getResults()) {
            List<Movie> movies = new ArrayList<>();
            PersonModel personModel = new PersonModel();
            personModel.setName(swapiPerson.getName());

            replaceUnknownValues(swapiPerson, personModel);

            personModel.setBirthYear(swapiPerson.getBirthYear());
            personModel.setId(getIdFromUrl(swapiPerson));

            for (String film : swapiPerson.getFilms()) {
                SWAPIMovie swapiMovie = getMovie(film);
                if(swapiMovie != null) {
                    MovieModel movieModel = movieMapper.mapToMovieModel(swapiMovie);
                    movies.add(movieModel);
                }
            }
            personModel.setMovies(movies);
            persons.add(personModel);
        }
        return persons;
    }

    private SWAPIMovie getMovie(String film) {
        return restTemplate.getForObject(film, SWAPIMovie.class);
    }

    /**
     * Returns a single person by ID
     * @param result the person found in String format from the Star Wars API
     * @return the person found
     * @throws JsonProcessingException exception through mapping
     */
    public Person mapToPersonModel(String result) throws JsonProcessingException {
        SWAPIPerson swapiPerson = objectMapper.readValue(result, SWAPIPerson.class);
        MovieMapper movieMapper = new MovieMapper(objectMapper);

        List<Movie> movies = new ArrayList<>();
        PersonModel personModel = new PersonModel();
        personModel.setName(swapiPerson.getName());

        replaceUnknownValues(swapiPerson, personModel);

        personModel.setBirthYear(swapiPerson.getBirthYear());
        personModel.setId(getIdFromUrl(swapiPerson));

        for (String film : swapiPerson.getFilms()) {
            SWAPIMovie swapiMovie = getMovie(film);
            if (swapiMovie != null) {
                MovieModel movieModel = movieMapper.mapToMovieModel(swapiMovie);
                movies.add(movieModel);
            }
        }
        personModel.setMovies(movies);
        return personModel;
    }

    /**
     * Mapping unknown values from Star Wars API to known values
     * @param swapiPerson The SWAPIPPerson mapped from the Star Wars API result
     * @param personModel The PersonModel to map swapiPerson to
     */
    private void replaceUnknownValues(SWAPIPerson swapiPerson, PersonModel personModel) {
        if (physiqueMap.containsKey(swapiPerson.getMass())) {
            personModel.setWeight(physiqueMap.get(swapiPerson.getMass()));
        } else if (swapiPerson.getMass().contains(",")) {
            String correctedMass = swapiPerson.getMass().replace(",", ".");
            swapiPerson.setMass(correctedMass);
            personModel.setWeight((int) Math.floor(Double.parseDouble(correctedMass)));
        } else if (swapiPerson.getMass().contains(".")) {
            personModel.setWeight((int) Math.floor(Double.parseDouble(swapiPerson.getMass())));
        } else {
            personModel.setWeight(Integer.parseInt(swapiPerson.getMass()));
        }

        if (physiqueMap.containsKey(swapiPerson.getHeight())) {
            personModel.setHeight(physiqueMap.get(swapiPerson.getHeight()));
        } else if (swapiPerson.getHeight().contains(".")) {
            personModel.setHeight((int) Math.floor(Double.parseDouble(swapiPerson.getHeight())));
        } else {
            personModel.setHeight(Integer.parseInt(swapiPerson.getHeight()));
        }

        if (genderMap.containsKey(swapiPerson.getGender())) {
            personModel.setGender(genderMap.get(swapiPerson.getGender()));
        } else {
            personModel.setGender(Gender.valueOf(swapiPerson.getGender().toUpperCase()));
        }
    }

    /**
     * Getting id from URL field in person result
     * @param swapiPerson The person to get id from
     * @return the id
     */
    public long getIdFromUrl(SWAPIPerson swapiPerson) {
        String[] url = swapiPerson.getUrl().split("/");

        return Long.parseLong(url[url.length - 1]);
    }
}
