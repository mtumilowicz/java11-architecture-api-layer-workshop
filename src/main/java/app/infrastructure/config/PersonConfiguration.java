package app.infrastructure.config;

import app.domain.person.PersonRepository;
import app.domain.person.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

    @Bean
    public PersonService xService(PersonRepository personRepository) {
        return new PersonService(personRepository);
    }

}
