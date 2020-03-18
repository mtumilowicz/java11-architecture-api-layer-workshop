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

    public static PersonEntity from(Person person) {
        return PersonEntity.builder()
                .name(person.getName())
                .build();
    }

    public Person rebuild(Person person) {
        return person.toBuilder()
                // set all field that hibernate fills - ex. Version
                .build();
    }
}
