package app.domain.person;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder(toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Person {

    String id;

    String name;

    String surname;

    static Person createFrom(NewPersonCommand command) {
        return Person.builder()
                .name(command.getName())
                .surname(command.getSurname())
                .build();
    }
}
