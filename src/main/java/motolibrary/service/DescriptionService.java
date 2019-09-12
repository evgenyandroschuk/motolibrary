package motolibrary.service;

import motolibrary.model.Languages;

public interface DescriptionService {

    String getDescriptionById(Languages lang, long id);

}
