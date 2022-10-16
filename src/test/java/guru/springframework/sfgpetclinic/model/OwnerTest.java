package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTest {
    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1l, "joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");
        assertAll("propertyTest",
                () -> assertAll("Person Properties",
                        () -> assertEquals("joe", owner.getFirstName(), "first name didnot match"),
                        () -> assertEquals("Buck", owner.getLastName()),
                        () -> assertAll("Owner Properties",
                                () -> assertEquals("Key West", owner.getCity(), "city didnot match"),
                                () -> assertEquals("1231231234", owner.getTelephone())
                        )

                )

        );
        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ValueSource(strings = {"Spring", "FrameWork", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @EnumSource(OwnerType.class)
    void testEnumSource(OwnerType ownerType) {
        System.out.println(ownerType);

    }

    @DisplayName(" CSV Source Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvSource({
            "TH,0,21",
            "KR,0,22",
            "MZ,0,23"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);

    }

    @DisplayName(" CSV File Source Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFileInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);

    }

    @DisplayName(" Method Provider Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "-" + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(" TH", 2, 3),
                Arguments.of(" FL", 4, 5),
                Arguments.of(" CA", 6, 8),
                Arguments.of(" KR", 11, 7)

        );
    }

    @DisplayName(" Custom Provider Test ")
    @ParameterizedTest(name = "{displayName} - [{index}] {arguments}")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2) {
        System.out.println(stateName + ":" + val1 + "-" + val2);
    }
}
