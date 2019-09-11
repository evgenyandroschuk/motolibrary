package motolibrary.service.dao.made;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MadeDaoConfiguration {

    @Bean
    public MadeDao madeDao() {
        return new MadeDaoInMemoryImpl();
    }

}
