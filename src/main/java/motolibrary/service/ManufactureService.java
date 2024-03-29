package motolibrary.service;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.model.ModelShortDetails;

import java.util.List;

public interface ManufactureService {

    List<Manufacture> getSortedManufacture();

    void createManufacture(Manufacture manufacture);

    void createModel(MainModel mainModel);

    void updateModel(MainModel mainModel);

    MainModel findModelById(Long id);

    List<ModelShortDetails> getModelsByManufacture(int id);
}
