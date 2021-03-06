package app.gateway.answers.person.output;

import app.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class PersonApiOutput {

    String id;

    String name;

    String surname;

    public static PersonApiOutput from(Person person) {
        return PersonApiOutput.builder()
                .id(person.getId())
                .name(person.getName())
                .surname(person.getSurname())
                .build();
    }
}
