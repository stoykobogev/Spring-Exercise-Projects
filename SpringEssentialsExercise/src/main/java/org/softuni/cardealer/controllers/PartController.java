package org.softuni.cardealer.controllers;

import org.modelmapper.ModelMapper;
import org.softuni.cardealer.entities.Part;
import org.softuni.cardealer.models.binding.PartBindingModel;
import org.softuni.cardealer.services.PartService;
import org.softuni.cardealer.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController {

    private final SupplierService supplierService;
    private final PartService partService;

    @Autowired
    public PartController(SupplierService supplierService, PartService partService) {
        this.supplierService = supplierService;
        this.partService = partService;
    }

    @GetMapping("/add")
    public ModelAndView addGet(ModelAndView modelAndView) {
        List<String> supplierNames = this.supplierService.getAllNames();
        modelAndView.addObject("supplierNames", supplierNames);
        modelAndView.setViewName("parts/add");

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam String quantity,
                                @RequestParam String supplierName,
                                ModelAndView modelAndView) {

        String exceptionMessage = this.partService.validateParams(name, supplierName);
        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView = this.addGet(modelAndView);
        } else {
            this.partService.persistPart(name, price, quantity, supplierName);
            modelAndView.setViewName("redirect:/parts/all");
        }

        return modelAndView;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<Part> parts = this.partService.getAll();

        modelAndView.addObject("parts", parts);
        modelAndView.setViewName("parts/all");

        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editGet(@RequestParam String price,
                                @RequestParam String name,
                                @RequestParam String quantity,
                                @RequestParam String supplierName,
                                ModelAndView modelAndView) {

        modelAndView.addObject("quantity", quantity);
        modelAndView.addObject("price", price);
        modelAndView.addObject("name", name);
        modelAndView.addObject("supplierName", supplierName);
        modelAndView.setViewName("parts/edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editPost(@RequestParam String quantity,
                                 @RequestParam String name,
                                 @RequestParam String supplierName,
                                 @RequestParam String price,
                                 ModelAndView modelAndView) {

        this.partService.updatePart(name, price, quantity, supplierName);
        modelAndView.setViewName("redirect:/parts/all");

        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteGet(@RequestParam String quantity,
                                  @RequestParam String name,
                                  @RequestParam String supplierName,
                                  @RequestParam String price,
                                  ModelAndView modelAndView) {

        modelAndView.addObject("quantity", quantity);
        modelAndView.addObject("name", name);
        modelAndView.addObject("supplierName", supplierName);
        modelAndView.addObject("price", price);
        modelAndView.setViewName("parts/delete");

        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deletePost(@RequestParam String name,
                                   @RequestParam String supplierName,
                                   ModelAndView modelAndView) {

        this.partService.deletePart(name, supplierName);

        modelAndView.setViewName("redirect:/parts/all");

        return modelAndView;
    }
}
