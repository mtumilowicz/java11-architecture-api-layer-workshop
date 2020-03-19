package app.domain.person;

import lombok.Builder;

@Builder
public class PersonCreationInput {
    String name;

    Person toPerson() {
        return Person.builder()
                .name(name)
                .build();
    }
}
