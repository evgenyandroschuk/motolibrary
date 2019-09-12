package motolibrary.service.dao.made;


import com.google.common.collect.ImmutableMap;
import motolibrary.model.Manufacture;
import motolibrary.service.dao.AbstractDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
}
