package motolibrary.service;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.service.dao.made.MadeDao;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ManufactureServiceTest {

    @InjectMocks
    private ManufactureServiceImpl manufactureService;

    @Mock
    private MadeDao madeDao;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void reset() {
        Mockito.reset(madeDao);
    }

    @Test(dataProvider = "manufactureProvider")
    public void testSortedManufacture(Set<Manufacture> manufactures, String one, String two) {
        Mockito.when(madeDao.getAllManufactures()).thenReturn(manufactures);

        List<Manufacture> result = manufactureService.getSortedManufacture();
        Assert.assertEquals(result.get(0).getDescription(), one);
        Assert.assertEquals(result.get(1).getDescription(), two);
    }

    @DataProvider
    private Object[][] manufactureProvider() {
        return new Object[][] {
            {
                getManufactureSet("AB", "AAA"),
                "AAA",
                "AB"
            },
            {
                getManufactureSet("CAA", "CA"),
                "CA",
                "CAA"
            }
        };
    }

    @Test
    public void testCreateManufacture() {
        Manufacture manufacture = new Manufacture(null, null, "Test", "Country");
        manufactureService.createManufacture(manufacture);
        Mockito.verify(madeDao).createManufacture(manufacture);
    }

    @Test
    public void testCreateModel() {
        MainModel mainModel = new MainModel(7, "FZ1-S",2006, 2015);
        mainModel.setType("Roadster");
        manufactureService.createModel(mainModel);
        Mockito.verify(madeDao).createModel(mainModel);

    }


    private Set<Manufacture> getManufactureSet(String one, String two) {
        Set<Manufacture> manufactureSet = new TreeSet<>();
        manufactureSet.add(new Manufacture(null, null, one, "-"));
        manufactureSet.add(new Manufacture(null, null, two, "-"));
        return manufactureSet;
    }


}
