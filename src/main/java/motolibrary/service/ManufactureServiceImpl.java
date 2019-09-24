package motolibrary.service;

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
        madeDao.createModel(manufacture);
    }
}
