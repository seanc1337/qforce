package nl.qnh.qforce.SWAPIObject;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SWAPIPerson implements Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;
    public String birth_year;
    public String gender;
    public String height;
    @JsonAlias("mass")
    public String weight;
    @Column
    @ElementCollection(targetClass = SWAPIMovie.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public List<Movie> movies;
    public String[] films;

    @JsonIgnore
    public Integer count;
    @JsonIgnore
    public String next;
    @JsonIgnore
    public String previous;
    @JsonIgnore
    public String[] results;
    @JsonIgnore
    public String eye_color;
    @JsonIgnore
    public String hair_color;
    @JsonIgnore
    public String skin_color;
    @JsonIgnore
    public String homeworld;
    @JsonIgnore
    public String[] species;
    @JsonIgnore
    public String[] starships;
    @JsonIgnore
    public String[] vehicles;
    @JsonIgnore
    public String url;
    @JsonIgnore
    public String created;
    @JsonIgnore
    public String edited;

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @JsonGetter("birth_year")
    @Override
    public String getBirthYear() {
        return this.birth_year;
    }

    @Override
    public Gender getGender() {
        return Gender.valueOf(this.gender.toUpperCase());
    }

    @Override
    public Integer getHeight() {
        return Integer.parseInt(this.height.replace(",", ""));
    }

    @Override
    public Integer getWeight() {
        return (int) Math.ceil(Double.parseDouble(this.weight.replace(",", "")));
    }

    @JsonSetter("films")
    @Override
    public List<Movie> getMovies() {
        return this.movies;
    }
}
