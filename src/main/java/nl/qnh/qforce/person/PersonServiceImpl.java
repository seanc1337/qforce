package nl.qnh.qforce.person;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.resources.SWAPIConfiguration;
import nl.qnh.qforce.response.ResponseMapper;
import nl.qnh.qforce.response.SWAPIResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * The person service to search and retrieve persons.
 *
 * @author Sean
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final RestTemplate restTemplate;
    private final SWAPIConfiguration swapiConfiguration;
    private final PersonMapper personMapper;
    private final ResponseMapper responseMapper;

    public PersonServiceImpl(RestTemplate restTemplate, final SWAPIConfiguration swapiConfiguration,
                             final PersonMapper personMapper, final ResponseMapper responseMapper) {
        this.restTemplate = restTemplate;
        this.swapiConfiguration = swapiConfiguration;
        this.personMapper = personMapper;
        this.responseMapper = responseMapper;
    }

    /**
     * Searches for persons.
     *
     * @param query the query string
     * @return the list of persons
     */
    @Override
    public List<Person> search(String query) {
        String personsQuery = getPeopleEndpoint() + "?search=" + query;
        String personResult = restTemplate.getForObject(personsQuery, String.class);
        SWAPIResponse response = responseMapper.mapToSWAPIResponse(personResult);
        List<Person> persons = personMapper.mapToPersonModel(response);
        while (response.getNext() != null) {
            String nextResult = restTemplate.getForObject(response.getNext(), String.class);
            SWAPIResponse nextResponse = responseMapper.mapToSWAPIResponse(nextResult);
            persons.addAll(personMapper.mapToPersonModel(nextResponse));
            response = nextResponse;
        }

        return persons;
    }

    /**
     * Returns the person with the provided id.
     *
     * @param id the id of the person
     * @return the person
     */
    @Override
    public Optional<Person> get(long id) {
        String query = getPeopleEndpoint() + "/" + id;
        String personResult = restTemplate.getForObject(query, String.class);
        Optional<Person> newPerson = Optional.empty();
        try {
            Person person = personMapper.mapToPersonModel(personResult);
            newPerson = Optional.of(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return newPerson;
    }

    private String getPeopleEndpoint() {
        return swapiConfiguration.getBaseURL() + swapiConfiguration.getPeopleEndpoint();
    }
}

