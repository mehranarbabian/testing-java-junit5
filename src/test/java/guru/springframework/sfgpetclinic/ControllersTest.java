package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("controllers")
public interface ControllersTest {
    @BeforeAll
     default void beforeAll() {
        System.out.println("Lets Do Something here");

    }
}
