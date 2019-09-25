package motolibrary.service;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;

import java.util.List;

public interface ManufactureService {

    List<Manufacture> getSortedManufacture();

    void createManufacture(Manufacture manufacture);

    void createModel(MainModel mainModel);
}
