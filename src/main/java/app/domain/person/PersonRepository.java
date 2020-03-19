package app.domain.person;

import app.domain.Failures;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersonRepository {
    Either<Failures, Person> save(Person person);
    Option<Person> findById(String id);
}
