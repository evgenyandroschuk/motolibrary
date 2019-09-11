package motolibrary.service.dao.made;

import motolibrary.model.Manufacture;

import java.util.HashSet;
import java.util.Set;

public class MadeDaoInMemoryImpl implements MadeDao{
    @Override
    public Set<Manufacture> getAllManufactures() {

        Set<Manufacture> manufactures = new HashSet<>();
        manufactures.add(new Manufacture(1, 1, "Bajaj", "India"));
        manufactures.add(new Manufacture(2, 2, "BMW", "Germany"));
        return manufactures;
    }
}
