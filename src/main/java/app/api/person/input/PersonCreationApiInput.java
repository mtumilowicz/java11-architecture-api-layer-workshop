package app.api.person.input;

import app.domain.person.Person;
import app.domain.person.PersonCreationInput;

public class PersonCreationApiInput {
    String name;
    public PersonCreationInput toDomain() {
        return PersonCreationInput.builder()
                .name(name)
                .build();
    }
}
