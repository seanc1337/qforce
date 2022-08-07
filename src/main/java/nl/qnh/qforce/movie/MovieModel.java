package nl.qnh.qforce.movie;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonSetter;
import nl.qnh.qforce.domain.Movie;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class MovieModel implements Movie {

    @Id
    private Integer episodeId;
    private String title;
    private String director;
    private LocalDate releaseDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @JsonSetter("release_date")
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Integer getEpisode() {
        return episodeId;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
