package app.infrastructure.config;

import app.domain.person.PersonRepository;
import app.domain.person.PersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class PersonConfiguration {

    @Bean
    public PersonService xService(PersonRepository personRepository, TransactionTemplate transactionTemplate) {
        return new PersonService(personRepository, transactionTemplate);
    }

}
