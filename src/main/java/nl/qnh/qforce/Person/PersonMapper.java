package nl.qnh.qforce.Person;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.qnh.qforce.Person.SWAPIPerson;
import nl.qnh.qforce.domain.Gender;
import nl.qnh.qforce.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {
    private ObjectMapper objectMapper;

    public PersonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<SWAPIPerson> fromJsonToSWAPIPerson(String result) {
        List<SWAPIPerson> swapiPersons = new ArrayList<>();
        try {
            JsonNode json = objectMapper.readTree(result);

            String jsonResult = json.toString();

            SWAPIPerson swapiPersonToAdd = this.objectMapper.readValue(jsonResult, SWAPIPerson.class);
            swapiPersons.add(swapiPersonToAdd);

            return swapiPersons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return swapiPersons;
    }

//    public Person fromJsonToSWAPIPerson (String result, Long id) {
//        Person newPerson  = new SWAPIPerson();
//        try {
//            newPerson = this.objectMapper.readValue(result, SWAPIPerson.class);
//            ((SWAPIPerson) newPerson).id = id;
//
//            return newPerson;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return newPerson;
//    }
}
