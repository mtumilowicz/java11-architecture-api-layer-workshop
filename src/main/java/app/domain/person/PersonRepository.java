package app.domain.person;

import app.domain.Failure;
import io.vavr.control.Either;
import io.vavr.control.Option;

public interface PersonRepository {
    Either<Failure, Person> save(Person person);
    Option<Person> findById(String id);
}
