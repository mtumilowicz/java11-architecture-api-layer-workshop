package app.domain.person;

import app.domain.Failure;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public Either<Failure, Person> save(Person person) {
        return personRepository.save(person);
    }
}
