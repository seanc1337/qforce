package nl.qnh.qforce.person;

import nl.qnh.qforce.domain.Person;
import nl.qnh.qforce.resources.SWAPIConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceImplTest {

    @Autowired
    private PersonServiceImpl personService;
    @Autowired
    private SWAPIConfiguration configuration;

    @Test
    void search() {
        assertNotNull(personService);
        List<Person> persons = personService.search("L");
        assertFalse(persons.isEmpty());
        assertEquals(persons.size(), 37);
    }

    @Test
    void get() {
        assertNotNull(personService);
        Optional<Person> person = personService.get(1);
        assertTrue(person.isPresent());
        assertEquals(person.get().getId(), 1);
    }
}