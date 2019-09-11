package motolibrary.service.dao;

import org.postgresql.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;


@Configuration
public class DaoConfiguration {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        String url = System.getenv().get("DATABASE_URL");
        String user = System.getenv().get("DATABASE_USER");
        String password = System.getenv().get("DATABASE_PASSWORD");
        System.out.println("DATABASE_URL = " + url);
        return new SimpleDriverDataSource(new Driver(), url, user, password);
    }

}
