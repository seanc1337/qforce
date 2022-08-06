package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Movie;
import nl.qnh.qforce.domain.Person;

import javax.persistence.*;
import java.util.List;

@Entity
public class PersonModel implements Person {

    @Id
    private long id;
    private String name;
    private String birthYear;
    private Gender gender;
    private Integer height;
    private Integer weight;
//    private List<Movie> movies;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBirthYear() {
        return birthYear;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public Integer getHeight() {
        return height;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }
}
