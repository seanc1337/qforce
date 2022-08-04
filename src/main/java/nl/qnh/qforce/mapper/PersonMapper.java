package nl.qnh.qforce.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.SWAPIObject.SWAPIPerson;
import nl.qnh.qforce.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {
    private ObjectMapper objectMapper;

    public PersonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<SWAPIPerson> fromJsonToSWAPIPerson (String result) {
        List<SWAPIPerson> swapiPersons = new ArrayList<>();
        try {
            JsonNode json = objectMapper.readTree(result);
            JsonNode jsonResultsArray = json.findValues("results").get(0);

            for (JsonNode resultContent : jsonResultsArray) {
                String personResult = resultContent.toString();
                String personUrlComplete = resultContent.findValue("url").toString();
                String[] personUrlSplit = personUrlComplete.split("/");
                String personUrlId = personUrlSplit[personUrlSplit.length - 2];
                SWAPIPerson swapiPersonToAdd = this.objectMapper.readValue(personResult, SWAPIPerson.class);
                swapiPersonToAdd.id = Long.parseLong(personUrlId);
                swapiPersons.add(swapiPersonToAdd);
            }
            return swapiPersons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swapiPersons;
    }

    public Person fromJsonToSWAPIPerson (String result, Long id) {
        Person newPerson  = new SWAPIPerson();
        try {
            newPerson = this.objectMapper.readValue(result, SWAPIPerson.class);
            ((SWAPIPerson) newPerson).id = id;

            return newPerson;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newPerson;
    }
}
