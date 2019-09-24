package motolibrary.controller;

import motolibrary.model.Manufacture;
import motolibrary.service.dao.made.MadeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    private final MadeDao madeDao;

    @Autowired
    public MainController(MadeDao madeDao) {
        this.madeDao = madeDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("manufactures", madeDao.getAllManufactures());
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "manufacture/create")
    public String createMake(
    ) {
        return "manufacture/create_make";
    }

    @RequestMapping(method = RequestMethod.GET, value = "manufacture/create/response")
    public String createMakeResponse(
        Model model,
        @RequestParam String description,
        @RequestParam String country
    ) {
        Manufacture manufacture = new Manufacture(null, null, description, country);
        model.addAttribute("manufacture", manufacture);
        try {
            madeDao.createModel(manufacture);
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "Manufacture " + manufacture.getDescription() + " already exists!");
            return "common_error";
        }
        return "manufacture/create_make_response";
    }

}
