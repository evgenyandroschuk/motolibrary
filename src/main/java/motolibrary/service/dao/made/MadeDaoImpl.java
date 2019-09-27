package motolibrary.service.dao.made;


import com.google.common.collect.ImmutableMap;
import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.service.dao.AbstractDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MadeDaoImpl extends AbstractDao implements MadeDao {

    public MadeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    @Override
    public Set<Manufacture> getAllManufactures() {
        String query = "select * from manufacture";
        List<Manufacture> manufactureList = namedParameterJdbcTemplate.query(query, ImmutableMap.of(), rs -> {
            List<Manufacture> manufactures = new LinkedList<>();
            while (rs.next()) {
                manufactures.add(
                    new Manufacture(
                        Optional.of(rs.getLong("id")).orElse(null),
                        Optional.of(rs.getLong("resource_id")).orElse(null),
                        Optional.of(rs.getString("description")).orElse(""),
                        Optional.of(rs.getString("country")).orElse("")
                    )
                );
            }
            return manufactures;
        });
        return new HashSet<>(Objects.requireNonNull(manufactureList));
    }

    @Override
    public void createManufacture(Manufacture manufacture) {
        String query = "insert into manufacture (id, resource_id, description, country)\n" +
            "values(nextval('manufacture_seq'), 1, :description, :country)";
        Map<String, Object> params = ImmutableMap.of(
            "description", manufacture.getDescription().toUpperCase(),
            "country", manufacture.getCountry()
            );

        namedParameterJdbcTemplate.execute(query, params, PreparedStatement::execute);
    }

    @Override
    public void createModel(MainModel mainModel) {
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
        Map<String, Object> params = getParamsByModel(mainModel);
        namedParameterJdbcTemplate.execute(query, params, PreparedStatement::execute);
    }

    @Override
    public void updateModel(MainModel mainModel) {
        String query = "update model\n" +
            "set  manufacture_id = :manufactureId, description = :description, start_year = :startYear, end_year = :endYear,\n" +
            "type = :type, final_drive = :finalDrive, transmission = :transmission, cc = :cc, power = :power, torque = :torque,\n" +
            "top_speed = :topSpeed, compression = :compression, rake_angle = :rakeAngle, trail = :trail, brakes_front = :brakesFront,\n" +
            "brakes_rear = :brakesRear, tires_front = :tiresFront, tires_rear = :tiresRear, length = :length, width = :width,\n" +
            "height = :height, seat_height = :seatHeight, wheel_base = :wheelBase, fuel_capacity = :fuelCapacity,\n" +
            "fuel_consumption = :fuelConsumption, dry_weight = :dryWeight, wet_weight = :wetWeight\n" +
            "where id = :id";
        Map<String, Object> params = getParamsByModelWithId(mainModel);
        namedParameterJdbcTemplate.execute(query, params, PreparedStatement::execute);
    }

    @Override
    public MainModel findModelById(Long id) {
        String query =
            "select id, manufacture_id, description, start_year, end_year, type, final_drive, transmission,\n" +
                "cc, power, torque, top_speed, compression, rake_angle, trail, brakes_front, brakes_rear,\n" +
                "tires_front, tires_rear, length, width, height, seat_height, wheel_base,\n" +
                "fuel_capacity, fuel_consumption, dry_weight, wet_weight from model\n" +
                "where id = :id";
        Map<String, Object> params = ImmutableMap.of("id", id);
        return namedParameterJdbcTemplate.query(query, params, rs -> {
            MainModel model = null;
            if(rs.next()) {
                int manufactureId = rs.getInt("manufacture_id");
                String description = rs.getString("description");
                Integer startYear = Optional.ofNullable(rs.getInt("start_year")).orElse(null);
                Integer endYear = Optional.ofNullable(rs.getInt("end_year")).orElse(null);
                String type = rs.getString("type");
                String finalDrive = rs.getString("final_drive");
                String transmission = rs.getString("transmission");
                String cc = rs.getString("cc");
                String power = rs.getString("power");
                String torque = rs.getString("torque");
                String topSpeed = rs.getString("top_speed");
                String compression = rs.getString("compression");
                String rakeAngle = rs.getString("rake_angle");
                String trail = rs.getString("trail");
                String brakesFront = rs.getString("brakes_front");
                String brakesRear = rs.getString("brakes_rear");
                String tiresFront = rs.getString("tires_front");
                String tiresRear = rs.getString("tires_rear");
                String length = rs.getString("length");
                String width = rs.getString("width");
                String height = rs.getString("height");
                String seatHeight = rs.getString("seat_height");
                String wheelBase = rs.getString("wheel_base");
                String fuelCapacity = rs.getString("fuel_capacity");
                String fuelConsumption = rs.getString("fuel_consumption");
                String dryWeight = rs.getString("dry_weight");
                String wetWeight = rs.getString("wet_weight");

                model = new MainModel(manufactureId, description, startYear, endYear);
                model.setId(id);
                model.setType(type);
                model.setFinalDrive(finalDrive);
                model.setTransmission(transmission);
                model.setCc(cc);
                model.setPower(power);
                model.setTorque(torque);
                model.setTopSpeed(topSpeed);
                model.setCompression(compression);
                model.setRakeAngle(rakeAngle);
                model.setTrail(trail);
                model.setBrakesFront(brakesFront);
                model.setBrakesRear(brakesRear);
                model.setTiresFront(tiresFront);
                model.setTiresRear(tiresRear);
                model.setLength(length);
                model.setWidth(width);
                model.setHeight(height);
                model.setSeatHeight(seatHeight);
                model.setWheelBase(wheelBase);
                model.setFuelCapacity(fuelCapacity);
                model.setFuelConsumption(fuelConsumption);
                model.setDryWeight(dryWeight);
                model.setWetWeight(wetWeight);
            }
            return model;
        });
    }

    private Map<String, Object> getParamsByModel(MainModel mainModel) {
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
        return params;
    }

    private Map<String, Object> getParamsByModelWithId(MainModel mainModel) {
        Map<String, Object> params = getParamsByModel(mainModel);
        params.put("id", mainModel.getId());
        return params;
    }


}
