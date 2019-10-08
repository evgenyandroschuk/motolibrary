package motolibrary.controller;

import motolibrary.model.Manufacture;
import motolibrary.model.ModelShortDetails;
import motolibrary.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class MainController {

    private final ManufactureService manufactureService;

    @Autowired
    public MainController(ManufactureService manufactureService) {
        this.manufactureService = manufactureService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("manufactures", manufactureService.getSortedManufacture());
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
            manufactureService.createManufacture(manufacture);
        } catch (DuplicateKeyException e) {
            model.addAttribute("message", "Manufacture " + manufacture.getDescription() + " already exists!");
            return "common_error";
        }
        return "manufacture/create_make_response";
    }

    @RequestMapping(method = RequestMethod.GET, value = "models")
    public String getModels(Model model, @RequestParam Integer manufactureId, @RequestParam String manufacture) {
        List<ModelShortDetails> models = manufactureService.getModelsByManufacture(manufactureId);
        model.addAttribute("manufacture", manufacture);
        model.addAttribute("modelDetails", models);

        return "model/models";
    }

}
