package app.infrastructure.person;

import app.domain.results.ErrorCode;
import app.domain.results.Failures;
import app.domain.results.Results;
import app.domain.person.Person;
import app.domain.person.PersonRepository;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDbRepository implements PersonRepository {

    @Autowired
    private PersonJpaRepository jpaRepository;


    @Override
    public Either<Failures, Person> save(Person person) {
        try {
            PersonEntity entity = jpaRepository.save(PersonEntity.from(person));
            return Results.success(entity.toDomain());
        } catch (RuntimeException ex) {
            return Results.appError(ErrorCode.SERVER_ERROR, "Database error.", ex);
        }

    }

    @Override
    public Option<Person> findById(String id) {
        return Option.ofOptional(jpaRepository.findById(id).map(PersonEntity::toDomain));
    }

    @Override
    public Either<Failures, List<Person>> findByName(String name) {
        return Results.success(jpaRepository.findByName(name));
    }
}
