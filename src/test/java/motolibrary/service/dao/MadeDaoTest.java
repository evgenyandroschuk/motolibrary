package motolibrary.service.dao;

import com.google.common.collect.ImmutableMap;
import motolibrary.model.MainModel;
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

import java.util.HashMap;
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

        madeDao.createManufacture(manufacture);
        verify(namedParameterJdbcTemplate).execute(
            eq(query), eq(params),
            ((PreparedStatementCallback<Boolean>) any(PreparedStatementCallback.class))
        );

    }

    @Test
    public void testCreateModel() {
        MainModel mainModel = new MainModel(7, "FZ1-S",2006, 2015);

        mainModel.setType("Roadster");
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

        String query =
            "insert into model(id, manufacture_id, description, start_year, end_year, type, final_drive, transmission,\n" +
            "cc, power, torque, top_speed, compression, rake_angle, trail, brakes_front, brakes_rear,\n" +
            "tires_front, tires_rear, length, width, height, seat_height, wheel_base,\n" +
            "fuel_capacity, fuel_consumption, dry_weight, wet_weight)\n" +
            "values(nextval('model_seq'), :manufactureId, :description, :startYear, :endYear, :type, :finalDrive,\n" +
            ":transmission, :cc, :power, :torque, :topSpeed, :compression, :rakeAngle, :trail, :brakesFront, :brakesRear,\n" +
            ":tiresFront, :tiresRear, :length, :width, :height, :seatHeight, :wheelBase,\n" +
            ":fuelCapacity, :fuelConsumption, :dryWeight, :wetWeight" +
            ")";
        Map<String, Object> params = new HashMap<>();
            params.put("manufactureId", mainModel.getManufactureId());
            params.put("description", mainModel.getDescription().toUpperCase());
            params.put("startYear", mainModel.getStartYear());
            params.put("endYear", mainModel.getEndYear());
            params.put("type", mainModel.getType());
            params.put("finalDrive", mainModel.getFinalDrive());
            params.put("transmission", mainModel.getTransmission());
            params.put("cc", mainModel.getCc());
            params.put("power", mainModel.getPower());
            params.put("torque", mainModel.getTorque());
            params.put("topSpeed", mainModel.getTopSpeed());
            params.put("compression", mainModel.getCompression());
            params.put("rakeAngle", mainModel.getRakeAngle());
            params.put("trail", mainModel.getTrail());
            params.put("brakesFront", mainModel.getBrakesFront());
            params.put("brakesRear", mainModel.getBrakesRear());
            params.put("tiresFront", mainModel.getTiresFront());
            params.put("tiresRear", mainModel.getBrakesRear());
            params.put("length", mainModel.getLength());
            params.put("width", mainModel.getWidth());
            params.put("height", mainModel.getHeight());
            params.put("seatHeight", mainModel.getSeatHeight());
            params.put("wheelBase", mainModel.getWheelBase());
            params.put("fuelCapacity", mainModel.getFuelCapacity());
            params.put("fuelConsumption", mainModel.getFuelConsumption());
            params.put("dryWeight", mainModel.getDryWeight());
            params.put("wetWeight", mainModel.getWetWeight());

        when(namedParameterJdbcTemplate.execute(
            eq(query), eq(params),
            ((PreparedStatementCallback<Boolean>) any(PreparedStatementCallback.class)))
        ).thenReturn(true);

        madeDao.createModel(mainModel);

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
