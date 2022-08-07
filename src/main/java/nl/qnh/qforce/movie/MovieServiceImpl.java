//package nl.qnh.qforce.movie;
//
//import nl.qnh.qforce.domain.Movie;
//import nl.qnh.qforce.domain.MovieModel;
//import nl.qnh.qforce.domain.Person;
//import nl.qnh.qforce.domain.PersonModel;
//import nl.qnh.qforce.person.SWAPIPerson;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class MovieServiceImpl implements MovieService{
//
//    private final RestTemplate restTemplate;
//    private final MovieMapper movieMapper;
//
//    public MovieServiceImpl(RestTemplate restTemplate, MovieMapper movieMapper) {
//        this.restTemplate = restTemplate;
//        this.movieMapper = movieMapper;
//    }
//
//    @Override
//    public List<Person> get(List<SWAPIPerson> swapiPersons, List<Person> persons) {
//        int index = 0;
//        for (SWAPIPerson swapiPerson : swapiPersons) {
//            for (String film : swapiPerson.getFilms()) {
//                SWAPIMovie movie = restTemplate.getForObject(film, SWAPIMovie.class);
//                if (movie != null) {
//                    MovieModel movieModel = movieMapper.mapToMovieModel(movie);
//                    List<Movie> personMovies = persons.get(index).getMovies();
//                    personMovies.add(movieModel);
//                }
//            }
//            index++;
//        }
//        return persons;
//    }
//
//    @Override
//    public Person get(SWAPIPerson swapiPerson, Person person) {
//        return null;
//    }
//}
