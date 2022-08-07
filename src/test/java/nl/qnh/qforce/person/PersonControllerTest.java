package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        assertEquals(personFound.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getPersons() {
        assertNotNull(personController);
        assertNotNull(personService);
        ResponseEntity<List<Person>> persons = personController.getPersons("L");
        assertNotNull(persons);
        assertEquals(persons.getBody().size(), 37);
        assertEquals(persons.getStatusCode(), HttpStatus.OK);
    }
}