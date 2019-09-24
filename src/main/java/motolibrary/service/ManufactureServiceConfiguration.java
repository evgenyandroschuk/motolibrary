package motolibrary.service;

import motolibrary.service.dao.made.MadeDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManufactureServiceConfiguration {

    @Bean
    public ManufactureService manufactureService(MadeDao madeDao) {
        return new ManufactureServiceImpl(madeDao);
    }

}
