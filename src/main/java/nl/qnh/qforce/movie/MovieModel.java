package nl.qnh.qforce.movie;

import nl.qnh.qforce.domain.Movie;

import java.time.LocalDate;

public class MovieModel implements Movie {

    private String title;
    private Integer episodeId;
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
