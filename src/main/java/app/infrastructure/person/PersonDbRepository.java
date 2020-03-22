package app.infrastructure.person;

import app.domain.person.Person;
import app.domain.person.PersonRepository;
import app.domain.results.Failures;
import app.domain.results.Results;
import io.vavr.control.Either;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class PersonDbRepository implements PersonRepository {

    @Autowired
    private PersonJpaRepository jpaRepository;


    @Override
    public Either<Failures, Person> save(Person person) {
        try {
            PersonEntity entity = jpaRepository.save(PersonEntity.from(person));
            return Results.success(entity.toDomain());
        } catch (RuntimeException ex) {
            return Results.userError("Database error during person saving.");
        }
    }

    @Override
    public Option<Person> findById(String id) {
        return Option.ofOptional(jpaRepository.findById(id).map(PersonEntity::toDomain));
    }

    @Override
    public Either<Failures, List<Person>> findByName(String name) {
        var entities = jpaRepository.findByName(name);
        var persons = entities.stream().map(PersonEntity::toDomain).collect(Collectors.toList());
        return Results.success(persons);
    }

    @Override
    public Either<Failures, String> deleteById(String id) {
        try {
            jpaRepository.deleteById(id);
            return Results.success(id);
        } catch (RuntimeException ex) {
            return Results.userError("Database error during person saving.");
        }
    }

    @Override
    public Either<Failures, String> existsById(String id) {
        return jpaRepository.existsById(id)
                ? Results.success(id)
                : Results.userError("Person with id " + id + " not found.");
    }
}
