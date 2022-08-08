package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.domain.PersonModel;
import nl.qnh.qforce.response.ResponseMapper;
import nl.qnh.qforce.response.SWAPIResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonMapperTest {
    @Autowired
    PersonMapper personMapper;
    @Autowired
    ResponseMapper responseMapper;

    @Test
    void mapToPersonModels() {
        String fileLocationSuccess = "./src/test/resources/swapiresultstring";
        String jsonResult = readFileAsString(fileLocationSuccess);
        SWAPIResponse swapiResponse = responseMapper.mapToSWAPIResponse(jsonResult);

        List<Person> persons = personMapper.mapToPersonModels(swapiResponse);

        assertNotNull(persons);
        assertEquals(10, persons.size());
        assertEquals(ArrayList.class, persons.getClass());
    }

    @Test
    void mapToPersonModel() {
        String fileLocationSinglePerson =  "./src/test/resources/swapiresultstringsingleperson";
        String jsonResult = readFileAsString(fileLocationSinglePerson);
        Person person = personMapper.mapToPersonModel(jsonResult);

        assertNotNull(person);
        assertEquals(PersonModel.class, person.getClass());
    }

    @Test
    void getIdFromUrl() {
    }

    public String readFileAsString(String file) {
        String fileResult = "";
        try {
            fileResult = new String(Files.readAllBytes(Paths.get(file)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return fileResult;
    }
}