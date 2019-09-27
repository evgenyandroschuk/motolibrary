package motolibrary.service;

import motolibrary.model.MainModel;
import motolibrary.model.Manufacture;
import motolibrary.service.dao.made.MadeDao;

import java.util.List;
import java.util.stream.Collectors;

public class ManufactureServiceImpl implements ManufactureService {

    private final MadeDao madeDao;

    public ManufactureServiceImpl(MadeDao madeDao) {
        this.madeDao = madeDao;
    }

    @Override
    public List<Manufacture> getSortedManufacture() {
        return madeDao.getAllManufactures()
            .stream()
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public void createManufacture(Manufacture manufacture) {
        madeDao.createManufacture(manufacture);
    }

    @Override
    public void createModel(MainModel mainModel) {
        madeDao.createModel(mainModel);
    }

    @Override
    public void updateModel(MainModel mainModel) {
        if (mainModel.getId() == null) {
            throw new RuntimeException("Empty ID");
        }
        madeDao.updateModel(mainModel);
    }

    @Override
    public MainModel findModelById(Long id) {
        return madeDao.findModelById(id);
    }
}
