package app.domain.person;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class Person {

    private String id;

    private String name;

    private String surname;
}
