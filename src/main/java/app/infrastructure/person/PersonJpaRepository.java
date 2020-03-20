package app.infrastructure.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, String> {
    List<PersonEntity> findByName(String name);
}
