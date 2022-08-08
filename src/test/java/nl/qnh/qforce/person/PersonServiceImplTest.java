package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personService;

    @Test
    void search() {
        assertNotNull(personService);
        List<Person> persons = personService.search("L");
        assertFalse(persons.isEmpty());
        assertEquals(37, persons.size());

        List<Person> personsNotFound = personService.search("-1");
        assertTrue(personsNotFound.isEmpty());
    }

    @Test
    void get() {
        assertNotNull(personService);
        Optional<Person> person = personService.get(1);
        assertTrue(person.isPresent());
        assertEquals(1, person.get().getId());

        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            final Optional<Person> personNotFound = personService.get(0);
        });
        assertEquals("404 NOT FOUND: "
                + "\"" + "{" + "\"detail\":" + "\"Not found\"" + "}" + "\"", exception.getMessage());
    }
}