package motolibrary.service.dao;

import motolibrary.model.Manufacture;
import motolibrary.service.dao.made.MadeDao;
import motolibrary.service.dao.made.MadeDaoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

@EnableConfigurationProperties(JdbcSettings.class)
@SpringBootTest(classes = {
    MadeDaoConfiguration.class,
    DaoConfiguration.class,
    JdbcSettings.class
})
public class MadeDaoManualTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MadeDao madeDao;

    @Test(groups = "manual")
    public void testGetAllManufactures() {
        Set<Manufacture> results =  madeDao.getAllManufactures();
        Assert.assertTrue(!results.isEmpty());
    }

}
