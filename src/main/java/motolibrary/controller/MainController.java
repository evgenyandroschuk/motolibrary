package motolibrary.controller;

import motolibrary.service.dao.made.MadeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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

    @RequestMapping(method = RequestMethod.GET, value = "/ru/")
    public String indexRu(Model model) {
        model.addAttribute("manufactures", madeDao.getAllManufactures());
        return "index-ru";
    }

}
