package app.domain.person;

import app.domain.results.Failures;
import app.domain.results.Results;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonService {

    PersonRepository personRepository;

    public Either<Failures, Person> create(NewPersonCommand newPersonCommand) {
        return personRepository.save(Person.createFrom(newPersonCommand));
    }

    public Option<Person> findById(String id) {
        return personRepository.findById(id);
    }

    public Either<Failures, List<Person>> findByName(String name) {
        return Results.requireNotEmpty(personRepository.findByName(name), "There is no person with name " + name);
    }

    public Either<Failures, String> deleteById(String id) {
        return existsById(id).flatMap(personRepository::deleteById);
    }

    public Either<Failures, String> existsById(String id) {
        return personRepository.existsById(id);
    }
}
