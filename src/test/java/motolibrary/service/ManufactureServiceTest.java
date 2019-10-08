package motolibrary.service;

import com.google.common.collect.ImmutableList;
import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.model.ModelShortDetails;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(madeDao.getAllManufactures()).thenReturn(manufactures);

        List<Manufacture> result = manufactureService.getSortedManufacture();
        Assert.assertEquals(result.get(0).getDescription(), one);
        Assert.assertEquals(result.get(1).getDescription(), two);
    }

    @DataProvider
    private Object[][] manufactureProvider() {
        return new Object[][]{
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
        verify(madeDao).createManufacture(manufacture);
    }

    @Test
    public void testCreateModel() {
        MainModel mainModel = new MainModel(7, "FZ1-S", 2006, 2015);
        mainModel.setType("Roadster");
        manufactureService.createModel(mainModel);
        verify(madeDao).createModel(mainModel);
    }

    @Test(expectedExceptions = RuntimeException.class, expectedExceptionsMessageRegExp = "Empty ID")
    public void testUpdateModelError() {
        MainModel mainModel = new MainModel(7, "FZ1-S", 2006, 2015);
        manufactureService.updateModel(mainModel);
    }

    @Test
    public void testUpdateModel() {
        MainModel mainModel = new MainModel(5, "G310R", 2016, null);
        mainModel.setId(15L);
        mainModel.setType("Roadster");
        mainModel.setFinalDrive("O-ring chain");
        mainModel.setTransmission("Constant-Mesh 6-speed w/multi-plate clutch");
        mainModel.setCc("998");
        mainModel.setPower("150");
        mainModel.setTorque("106Nm 8000 RPM");
        mainModel.setTopSpeed("260 Km/h");
        mainModel.setCompression("compression");
        mainModel.setRakeAngle("25");
        mainModel.setTrail("109 mm");
        mainModel.setBrakesFront("Dual 320 mm floating discs; forged monoblock 4-piston Sumitomo calipers");
        mainModel.setBrakesRear("245mm disc w/ single-piston pin-slide Nissin caliper");
        mainModel.setTiresFront("120/70-ZR17 (58W)");
        mainModel.setTiresRear("190/50-ZR17");
        mainModel.setLength("84.3 in (2,141 mm)");
        mainModel.setWidth("30.3 in (770 mm)");
        mainModel.setHeight("47.4 in (1,204 mm)");
        mainModel.setSeatHeight("32.1 in (815 mm)");
        mainModel.setWheelBase("57.5 in (1,460 mm)");
        mainModel.setFuelCapacity("18L ; 3.4L reserve");
        mainModel.setFuelConsumption("5,85-6,46L/100km");
        mainModel.setDryWeight("204Kg");
        mainModel.setWetWeight("220");

        manufactureService.updateModel(mainModel);
        verify(madeDao).updateModel(mainModel);
    }

    @Test
    public void testFindModelById() {
        Long id = 1L;
        MainModel model = new MainModel(7, "CBF600SA", 2010, 2015);
        model.setId(id);
        when(madeDao.findModelById(id)).thenReturn(model);
        MainModel result = manufactureService.findModelById(id);
        verify(madeDao).findModelById(id);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetModelsByManufacture() {
        Integer id = 27;
        List<ModelShortDetails> result = ImmutableList.of(
            new ModelShortDetails(4, "FZ-1 S", 2006, 2015),
            new ModelShortDetails(7, "MT-07", 2014, 2018)
        );

        when(madeDao.getModelsByManufacture(id)).thenReturn(result);
        List<ModelShortDetails> expected = manufactureService.getModelsByManufacture(id);
        verify(madeDao).getModelsByManufacture(id);
        Assert.assertEquals(expected, result);
    }

    private Set<Manufacture> getManufactureSet(String one, String two) {
        Set<Manufacture> manufactureSet = new TreeSet<>();
        manufactureSet.add(new Manufacture(null, null, one, "-"));
        manufactureSet.add(new Manufacture(null, null, two, "-"));
        return manufactureSet;
    }

}
