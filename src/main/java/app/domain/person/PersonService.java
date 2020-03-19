package app.domain.person;

import app.domain.ErrorCode;
import app.domain.Failures;
import app.domain.Results;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static java.util.function.Predicate.not;

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
        return personRepository.findByName(name)
                .filter(not(List::isEmpty))
                .getOrElse(Results.userError(ErrorCode.VALIDATION_ERROR, "There is no person with name " + name));
    }
}
