package motolibrary.service;

import motolibrary.model.Manufacture;

import java.util.List;

public interface ManufactureService {

    List<Manufacture> getSortedManufacture();

    void createManufacture(Manufacture manufacture);
}
