package app.infrastructure.person;

import app.domain.person.Person;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class PersonEntity {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String name;

    private String surname;

    public static PersonEntity from(Person person) {
        return PersonEntity.builder()
                .name(person.getName())
                .surname(person.getSurname())
                .build();
    }

    public Person toDomain() {
        return Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .build();
    }
}
