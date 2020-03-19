package app.domain.person;

import lombok.Builder;

@Builder
public class PersonCreationInput {
    String name;

    String surname;

    Person toPerson() {
        return Person.builder()
                .name(name)
                .surname(surname)
                .build();
    }
}
