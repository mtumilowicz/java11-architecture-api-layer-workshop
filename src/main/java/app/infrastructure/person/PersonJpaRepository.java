package app.infrastructure.person;

import app.domain.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, String> {
    List<Person> findByName(String name);
}
