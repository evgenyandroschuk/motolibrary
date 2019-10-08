package motolibrary.service.dao.made;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.model.ModelShortDetails;

import java.util.List;
import java.util.Set;

public interface MadeDao {

    Set<Manufacture> getAllManufactures();

    void createManufacture(Manufacture manufacture);

    void createModel(MainModel mainModel);

    void updateModel(MainModel mainModel);

    MainModel findModelById(Long id);

    List<ModelShortDetails> getModelsByManufacture(int id);

}
