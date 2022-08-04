package nl.qnh.qforce.SWAPIObject;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.qnh.qforce.domain.Movie;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SWAPIMovie implements Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String title;
    public Integer episode;
    public String director;
    public LocalDate release_date;

    @Override
    public String getTitle() {
        return this.title;
    }

    @JsonGetter("episode_id")
    @Override
    public Integer getEpisode() {
        return this.episode;
    }

    @Override
    public String getDirector() {
        return this.director;
    }

    @JsonGetter("release_date")
    @Override
    public LocalDate getReleaseDate() {
        return this.release_date;
    }
}
