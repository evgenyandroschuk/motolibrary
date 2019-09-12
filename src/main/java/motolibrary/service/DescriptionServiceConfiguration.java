package motolibrary.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DescriptionServiceConfiguration {

    @Bean
    public DescriptionService descriptionService(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        return new DescriptionServiceImpl(namedParameterJdbcTemplate);
    }

}
