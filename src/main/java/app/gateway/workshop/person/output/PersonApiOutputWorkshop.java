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

    // id, name, surname

    public static PersonApiOutputWorkshop from(Person person) {
        // map person to output, hint: PersonApiOutputWorkshop
        return null;
    }
}
