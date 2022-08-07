package nl.qnh.qforce.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.domain.MovieModel;

/**
 * Mapper class for mapping SWAPIMovies to MovieModels
 * @author Sean
 */
public class MovieMapper {
    public ObjectMapper objectMapper;

    public MovieMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Mapping SWAPIMovie to MovieModel
     * @param swapiMovie the SWAPIMovie object
     * @return the mappped MovieModel object
     */
    public MovieModel mapToMovieModel(SWAPIMovie swapiMovie) {
        MovieModel movie = new MovieModel();
        movie.setDirector(swapiMovie.getDirector());
        movie.setTitle(swapiMovie.getTitle());
        movie.setReleaseDate(swapiMovie.getReleaseDate());
        movie.setEpisodeId(swapiMovie.getEpisodeId());

        return movie;
    }
}
