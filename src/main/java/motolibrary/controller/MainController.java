package motolibrary.controller;

import motolibrary.model.MainModel;
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
        model.addAttribute("manufactureId", manufactureId);
        model.addAttribute("modelDetails", models);

        return "model/models";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/details")
    public String getModelDetails(Model model, @RequestParam Long id) {
        MainModel mainModel = manufactureService.findModelById(id);
        Integer manufactureId = mainModel.getManufactureId();
        Manufacture manufacture = getManufactureById(manufactureId);
        model.addAttribute("mainModel", mainModel);
        model.addAttribute("manufacture", manufacture);
        return "model/model_details";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/add")
    public String createModel(
        Model model,
        @RequestParam Integer id,
        @RequestParam String description
    ) {
        model.addAttribute("id", id);
        model.addAttribute("description", description);
        return "model/model_details_add";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/add/response")
    public String createModelResponse(
        Model model,
        @RequestParam String description,
        @RequestParam String type,
        @RequestParam String start,
        @RequestParam String end,
        @RequestParam String finalDrive,
        @RequestParam String transmission,
        @RequestParam String cc,
        @RequestParam String power,
        @RequestParam String torque,
        @RequestParam String topSpeed,
        @RequestParam String compression,
        @RequestParam String rakeAngle,
        @RequestParam String trail,
        @RequestParam String brakesFront,
        @RequestParam String brakesRear,
        @RequestParam String tiresFront,
        @RequestParam String tiresRear,
        @RequestParam String length,
        @RequestParam String width,
        @RequestParam String height,
        @RequestParam String seatHeight,
        @RequestParam String wheelBase,
        @RequestParam String fuelCapacity,
        @RequestParam String fuelConsumption,
        @RequestParam String dryWeight,
        @RequestParam String wetWeight,
        @RequestParam Integer manufactureId
    ) {

        MainModel mainModel = new MainModel(
            manufactureId, description, Integer.valueOf(start),
            end.length() > 0 ? Integer.valueOf(end) : null
        );
        mainModel.setType(type);
        mainModel.setFinalDrive(finalDrive);
        mainModel.setTransmission(transmission);
        mainModel.setCc(cc);
        mainModel.setPower(power);
        mainModel.setTorque(torque);
        mainModel.setTopSpeed(topSpeed);
        mainModel.setCompression(compression);
        mainModel.setRakeAngle(rakeAngle);
        mainModel.setTrail(trail);
        mainModel.setBrakesFront(brakesFront);
        mainModel.setBrakesRear(brakesRear);
        mainModel.setTiresFront(tiresFront);
        mainModel.setTiresRear(tiresRear);
        mainModel.setLength(length);
        mainModel.setWidth(width);
        mainModel.setHeight(height);
        mainModel.setSeatHeight(seatHeight);
        mainModel.setWheelBase(wheelBase);
        mainModel.setFuelCapacity(fuelCapacity);
        mainModel.setFuelConsumption(fuelConsumption);
        mainModel.setDryWeight(dryWeight);
        mainModel.setWetWeight(wetWeight);

        model.addAttribute("mainModel", mainModel);
        model.addAttribute("manufacture", getManufactureById(manufactureId));
        manufactureService.createModel(mainModel);
        return "model/model_details_response";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/update")
    public String updateModel(
        Model model,
        @RequestParam Long id
    ) {
        MainModel mainModel = manufactureService.findModelById(id);
        model.addAttribute("mainModel", mainModel);
        return "model/model_details_update";
    }

    @RequestMapping(method = RequestMethod.GET, value = "model/update/response")
    public String updateModelResponse(
        Model model,
        @RequestParam String description,
        @RequestParam String type,
        @RequestParam String start,
        @RequestParam String end,
        @RequestParam String finalDrive,
        @RequestParam String transmission,
        @RequestParam String cc,
        @RequestParam String power,
        @RequestParam String torque,
        @RequestParam String topSpeed,
        @RequestParam String compression,
        @RequestParam String rakeAngle,
        @RequestParam String trail,
        @RequestParam String brakesFront,
        @RequestParam String brakesRear,
        @RequestParam String tiresFront,
        @RequestParam String tiresRear,
        @RequestParam String length,
        @RequestParam String width,
        @RequestParam String height,
        @RequestParam String seatHeight,
        @RequestParam String wheelBase,
        @RequestParam String fuelCapacity,
        @RequestParam String fuelConsumption,
        @RequestParam String dryWeight,
        @RequestParam String wetWeight,
        @RequestParam Integer manufactureId,
        @RequestParam Long id
    ) {

        MainModel mainModel = new MainModel(
            manufactureId, description, Integer.valueOf(start),
            end.length() > 0 ? Integer.valueOf(end) : null
        );
        mainModel.setId(id);
        mainModel.setType(type);
        mainModel.setFinalDrive(finalDrive);
        mainModel.setTransmission(transmission);
        mainModel.setCc(cc);
        mainModel.setPower(power);
        mainModel.setTorque(torque);
        mainModel.setTopSpeed(topSpeed);
        mainModel.setCompression(compression);
        mainModel.setRakeAngle(rakeAngle);
        mainModel.setTrail(trail);
        mainModel.setBrakesFront(brakesFront);
        mainModel.setBrakesRear(brakesRear);
        mainModel.setTiresFront(tiresFront);
        mainModel.setTiresRear(tiresRear);
        mainModel.setLength(length);
        mainModel.setWidth(width);
        mainModel.setHeight(height);
        mainModel.setSeatHeight(seatHeight);
        mainModel.setWheelBase(wheelBase);
        mainModel.setFuelCapacity(fuelCapacity);
        mainModel.setFuelConsumption(fuelConsumption);
        mainModel.setDryWeight(dryWeight);
        mainModel.setWetWeight(wetWeight);

        model.addAttribute("mainModel", mainModel);
        model.addAttribute("manufacture", getManufactureById(manufactureId));
        manufactureService.updateModel(mainModel);
        return "model/model_details_update_response";
    }

    private Manufacture getManufactureById(Integer manufactureId) {
        return manufactureService.getSortedManufacture()
            .stream().filter(t -> t.getId() == manufactureId)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("ManufactureId " + manufactureId + "not found"));
    }

}
