package app.domain.person;

import app.domain.Failure;
import io.vavr.control.Either;

public interface PersonRepository {
    Either<Failure, Person> save(Person person);
}
