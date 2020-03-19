package app.domain.person;

import app.domain.results.Failures;
import io.vavr.control.Either;
import io.vavr.control.Option;

import java.util.List;

public interface PersonRepository {

    Either<Failures, Person> save(Person person);

    Option<Person> findById(String id);

    Either<Failures, List<Person>> findByName(String name);
}
