package motolibrary.service.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;


@Configuration
public class DaoConfiguration {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource datasSource) {
        return new NamedParameterJdbcTemplate(datasSource);
    }

    @Bean
    public DataSource dataSource() {
        String url = System.getenv().get("DATABASE_URL");
        String user = System.getenv().get("DATABASE_USER");
        String password = System.getenv().get("DATABASE_PASSWORD");
        try {
            SimpleDriverDataSource dataSource =
                new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>)  Class.forName("org.postgresql.Driver");
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

}
