package motolibrary.service.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import motolibrary.model.Languages;
import motolibrary.model.ResourceCode;
import motolibrary.service.DescriptionServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;


public class DescriptionServiceTest {

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private DescriptionServiceImpl descriptionService;

    @BeforeTest
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void resetMocks() {
        reset(namedParameterJdbcTemplate);
    }

    @Test(dataProvider = "descriptionProvider")
    public void testGetDescriptionById(
        long resourceId,
        Languages en,
        List<ResourceCode> resourceCodes,
        String expectedDescription
    ) {
        String query = "select * from resource_code where resource_id = :resourceId";
        Map<String, Object> params = ImmutableMap.of("resourceId", resourceId);
        when(namedParameterJdbcTemplate.query(
            eq(query), eq(params), (ResultSetExtractor<List<ResourceCode>>) any(ResultSetExtractor.class)
        )).thenReturn(resourceCodes);

        String result = descriptionService.getDescriptionById(en, resourceId);
        Assert.assertEquals(result, expectedDescription);

    }

    @DataProvider
    private static Object[][] descriptionProvider() {
        return new Object[][] {
            {
                2L,
                Languages.EN,
                ImmutableList.of(),
                null
            },
            {
                2L,
                Languages.EN,
                ImmutableList.of(
                    new ResourceCode(2L, 1, "test")
                ),
                "test"
            },
            {
                2L,
                Languages.RU,
                ImmutableList.of(
                    new ResourceCode(2L, 1, "test")
                ),
                "test"
            },
            {
                2L,
                Languages.EN,
                ImmutableList.of(
                    new ResourceCode(2L, 1, "test"),
                    new ResourceCode(2L, 2, "Тест")
                ),
                "test"
            },
            {
                2L,
                Languages.RU,
                ImmutableList.of(
                    new ResourceCode(2L, 1, "test"),
                    new ResourceCode(2L, 2, "Тест")
                ),
                "Тест"
            }
        };
    }


    @TestConfiguration
    public static class DescriptionServiceTestConfig {

        @Bean
        public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
            return mock(NamedParameterJdbcTemplate.class);
        }

    }

}
