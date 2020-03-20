package app.infrastructure.config;

import app.domain.person.PersonRepository;
import app.domain.person.PersonService;
import app.gateway.transactional.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

    @Bean
    public PersonService personService(PersonRepository personRepository, Transaction transaction) {
        return new PersonService(personRepository, transaction);
    }
}
