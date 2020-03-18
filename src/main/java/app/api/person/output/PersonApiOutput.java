package app.api.person.output;

import app.api.ApiOutput;
import app.domain.person.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonApiOutput implements ApiOutput {

    String name;

    public static PersonApiOutput from(Person person) {
        return PersonApiOutput.builder()
                .name(person.getName())
                .build();
    }
}
