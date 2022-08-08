package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @Autowired
    private PersonServiceImpl personService;

    @Test
    void getPersonById() {
        assertNotNull(personController);
        assertNotNull(personService);
        ResponseEntity<Person> personFound = personController.getPersonById("1");
        assertNotNull(personFound);
        assertEquals(HttpStatus.OK, personFound.getStatusCode());
    }

    @Test
    void getPersons() {
        assertNotNull(personController);
        assertNotNull(personService);
        ResponseEntity<List<Person>> persons = personController.getPersons("L");
        assertNotNull(persons);
        assertEquals(37, Objects.requireNonNull(persons.getBody()).size());
        assertEquals(persons.getStatusCode(), HttpStatus.OK);
    }
}