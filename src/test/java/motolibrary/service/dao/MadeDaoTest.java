package motolibrary.service.dao;

import com.google.common.collect.ImmutableMap;
import motolibrary.model.Manufacture;
import motolibrary.service.dao.made.MadeDaoImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MadeDaoTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private MadeDaoImpl madeDao;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void resetMocks() {
        reset(namedParameterJdbcTemplate);
    }

    @Test
    public void testCreateMade() {
        Manufacture manufacture = new Manufacture(
            null, null, "test made", "test country"
        );
        String query = "insert into manufacture (id, resource_id, description, country)\n" +
            "values(nextval('manufacture_seq'), 1, :description, :country)";
        Map<String, Object> params = ImmutableMap.of(
            "description", manufacture.getDescription().toUpperCase(),
            "country", manufacture.getCountry()
        );

        when(namedParameterJdbcTemplate.execute(
            eq(query), eq(params),
            ((PreparedStatementCallback<Boolean>) any(PreparedStatementCallback.class)))
        ).thenReturn(true);

        madeDao.createModel(manufacture);
        verify(namedParameterJdbcTemplate).execute(
            eq(query), eq(params),
            ((PreparedStatementCallback<Boolean>) any(PreparedStatementCallback.class))
        );

    }

    @TestConfiguration
    public static class DescriptionServiceTestConfig {

        @Bean
        public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
            return mock(NamedParameterJdbcTemplate.class);
        }

    }

}
