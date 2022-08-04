package nl.qnh.qforce.service;

import nl.qnh.qforce.Repository.MovieRepository;
import nl.qnh.qforce.Repository.PersonRepository;
import nl.qnh.qforce.SWAPIObject.SWAPIMovie;
import nl.qnh.qforce.SWAPIObject.SWAPIPerson;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieRepository movieRepository;

    /**
     * Save list of persons to DB
     * @param persons List of persons
     * @return The list of persons
     */
    public List<SWAPIPerson> savePersons(@RequestBody List<SWAPIPerson> persons) {

        personRepository.saveAll(persons);

        return persons;
    }

    /**
     * Save the list of movies found per Person
     * @param movies List of movies per person
     * @return The list of movies
     */
    public List<Movie> saveMovies(@RequestBody List<Movie> movies) {
        List swapiMovies = movies;
        movieRepository.saveAll(swapiMovies);

        return movies;
    }

    /**
     * Searches for all persons
     * @param query the query string
     * @return The list of persons
     */
    @Override
    public List<Person> search(String query) {
        return (List) personRepository.findAll();
    }

    /**
     * Searches for a specific person
     * @param id the id of the person
     * @return the person found
     */
    @Override
    public Optional<Person> get(long id) {
        Optional<Person> person = (Optional) personRepository.findById(id);
        return person;
    }
}
