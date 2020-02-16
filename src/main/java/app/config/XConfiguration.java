package app.config;

import app.x.XRepository;
import app.x.XService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XConfiguration {

    @Bean
    public XService xService(XRepository xRepository) {
        return new XService(xRepository);
    }

}
