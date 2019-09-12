package motolibrary.service.dao.made;

import motolibrary.model.Manufacture;

import java.util.Set;

public interface MadeDao {

    Set<Manufacture> getAllManufactures();

    void createModel(Manufacture manufacture);

}
