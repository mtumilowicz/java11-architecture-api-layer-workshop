package app.infrastructure.person;

import app.domain.ErrorCode;
import app.domain.Failure;
import app.domain.Results;
import app.domain.person.Person;
import app.domain.person.PersonRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDbRepository implements PersonRepository {

    @Autowired
    private PersonJpaRepository jpaRepository;


    @Override
    public Either<Failure, Person> save(Person person) {
        try {
            PersonEntity entity = jpaRepository.save(PersonEntity.from(person));
            return Results.success(entity.rebuild(person));
        } catch (RuntimeException ex) {
            return Results.appError(ErrorCode.SERVER_ERROR, "Database error.", ex);
        }

    }
}
