package app.domain.person;

import app.domain.Failure;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public Either<Failure, Person> create(NewPersonCommand newPersonCommand) {
        return personRepository.save(Person.createFrom(newPersonCommand));
    }

    public Option<Person> findById(String id) {
        return personRepository.findById(id);
    }
}
