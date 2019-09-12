package motolibrary.service;

import com.google.common.collect.ImmutableMap;
import motolibrary.model.Languages;
import motolibrary.model.ResourceCode;
import motolibrary.service.dao.AbstractDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DescriptionServiceImpl extends AbstractDao implements DescriptionService {

    public DescriptionServiceImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    @Override
    public String getDescriptionById(Languages lang, long id) {

        String query = "select * from resource_code where resource_id = :resourceId";
        Map<String, Object> params = ImmutableMap.of("resourceId", id);

        List<ResourceCode> resourceCodes = namedParameterJdbcTemplate.query(query, params, rs -> {
            List<ResourceCode> results = new LinkedList<>();
            while (rs.next()) {
                results.add(
                    new ResourceCode(
                        rs.getLong("resource_id"),
                        rs.getInt("lang_id"),
                        Optional.of(rs.getString("description")).orElse("")
                    )
                );
            }
            return results;
        });


        return resolveResource(lang, resourceCodes);
    }

    private String resolveResource(Languages lang, List<ResourceCode> resourceCodes) {
        if (resourceCodes == null || resourceCodes.isEmpty()) {
            return null;
        }
        ResourceCode resourceCode = resourceCodes
            .stream()
            .filter(t -> t.getLangId() == lang.getId())
            .findFirst()
            .orElse(resourceCodes.stream().findFirst().get());

        return resourceCode.getDescription();
    }
}
