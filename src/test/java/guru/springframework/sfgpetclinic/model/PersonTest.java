package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest implements ModelTest {

    @Test
    void groupAssertions() {
        Person person = new Person(1l, "joe", "Buck");
        assertAll("Test Props Set",
                () -> assertEquals("joe", person.getFirstName()),
                () -> assertEquals("Buck", person.getLastName()));
    }

    @Test
    void groupAssertionsMsg() {
        Person person = new Person(1l, "joe", "Buck");
        assertAll("Test Props Set",
                () -> assertEquals("joe", person.getFirstName(), "first name failed"),
                () -> assertEquals("Buck", person.getLastName(), "last name failed"));
    }

}
