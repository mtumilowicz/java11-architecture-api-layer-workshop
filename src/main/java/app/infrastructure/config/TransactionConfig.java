package app.infrastructure.config;

import app.infrastructure.transactional.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class TransactionConfig {
    @Bean
    public Transaction transaction(TransactionTemplate template) {
        return new Transaction(template);
    }
}