package app.gateway.workshop.person.output;

import app.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonApiOutputWorkshop {

    String id;

    String name;

    String surname;

    public static PersonApiOutputWorkshop from(Person person) {
        return PersonApiOutputWorkshop.builder()
                .id(person.getId())
                .name(person.getName())
                .surname(person.getSurname())
                .build();
    }
}
