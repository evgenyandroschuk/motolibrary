package motolibrary.service.dao.made;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;

import java.util.Set;

public interface MadeDao {

    Set<Manufacture> getAllManufactures();

    void createManufacture(Manufacture manufacture);

    void createModel(MainModel mainModel);

}
