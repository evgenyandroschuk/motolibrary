package motolibrary.service.dao;

import motolibrary.model.MainModel;
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

//    @Test
    public void testCreateMade() {
        Manufacture manufacture = new Manufacture(
            null, null, "test made", "test country"
        );
        madeDao.createManufacture(manufacture);
    }

//    @Test
    public void testCreateModel() {
        MainModel mainModel = getDefaultMainModel();
        madeDao.createModel(mainModel);
    }

//    @Test
    public void testUpdateModel() {
        MainModel mainModel = getDefaultMainModel();
        mainModel.setId(1L);
        madeDao.updateModel(mainModel);
    }

    private MainModel getDefaultMainModel() {
        MainModel mainModel = new MainModel(27, "FZ1-S",2006, 2015);

        mainModel.setType("Roadster and naked");
        mainModel.setFinalDrive("O-ring chain");
        mainModel.setTransmission("Constant-Mesh 6-speed w/multi-plate clutch");
        mainModel.setCc("998");
        mainModel.setPower("150");
        mainModel.setTorque("106Nm 8000 RPM");
        mainModel.setTopSpeed("260 Km/h");
        mainModel.setCompression(null);
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
        return mainModel;
    }

}
