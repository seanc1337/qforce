package nl.qnh.qforce.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.Person.PersonModel;
import nl.qnh.qforce.Person.SWAPIPerson;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;

import java.util.List;

public class MovieMapper {
    public ObjectMapper objectMapper;

    public MovieMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public MovieModel mapToMovieModel(SWAPIMovie swapiMovie) {
        MovieModel movie = new MovieModel();
        movie.setDirector(swapiMovie.getDirector());
        movie.setTitle(swapiMovie.getTitle());
        movie.setReleaseDate(swapiMovie.getReleaseDate());
        movie.setEpisodeId(swapiMovie.getEpisodeId());

        return movie;
    }
}
