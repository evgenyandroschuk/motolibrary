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

        namedParameterJdbcTemplate.execute(query, params, PreparedStatement::execute);
    }


}
