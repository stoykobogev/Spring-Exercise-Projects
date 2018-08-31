package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Supplier;
import org.softuni.cardealer.models.view.SupplierViewModel;
import org.softuni.cardealer.services.SupplierService;
import org.softuni.cardealer.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    private final Logger logger;

    @Autowired
    public SupplierController(SupplierService supplierService, Logger logger) {
        this.supplierService = supplierService;
        this.logger = logger;
    }

    @GetMapping("/local")
    public ModelAndView local(ModelAndView modelAndView) {
        List<SupplierViewModel> supplierList = this.supplierService.getAllLocalSuppliersViewModel();

        modelAndView.addObject("suppliers", supplierList);
        modelAndView.setViewName("suppliers/all");

        return modelAndView;
    }

    @GetMapping("/importers")
    public ModelAndView importers(ModelAndView modelAndView) {
        List<SupplierViewModel> supplierList = this.supplierService.getAllImportingSuppliersViewModel();

        modelAndView.addObject("suppliers", supplierList);
        modelAndView.setViewName("suppliers/all");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addGet(ModelAndView modelAndView) {
        modelAndView.setViewName("suppliers/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(ModelAndView modelAndView,
                                @RequestParam Boolean isImporter,
                                @RequestParam String name,
                                HttpSession session) {

        String exceptionMessage = this.supplierService.validateSupplier(name);

        if (exceptionMessage != null) {
            modelAndView.addObject("exceptionMessage", exceptionMessage);
            modelAndView.setViewName("suppliers/add");
        } else {
            this.supplierService.persistSupplier(name, isImporter);
            this.logger.logEvent(session, "Add", "Supplier");
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editGet(ModelAndView modelAndView, @PathVariable Long id) {
        Supplier supplier = this.supplierService.getSupplierById(id);

        modelAndView.addObject("supplier", supplier);
        modelAndView.setViewName("suppliers/edit");

        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(ModelAndView modelAndView,
                                 RedirectAttributes redirectAttributes,
                                 @PathVariable Long id,
                                 @RequestParam String newName,
                                 @RequestParam String oldName,
                                 @RequestParam Boolean isImporter,
                                 HttpSession session) {

        String exceptionMessage = this.supplierService.validateSupplier(oldName, newName);

        if (exceptionMessage != null) {
            redirectAttributes.addFlashAttribute("exceptionMessage", exceptionMessage);
            modelAndView.setViewName("redirect:/suppliers/edit/" + id);
        } else {
            this.supplierService.updateSupplier(id, newName, isImporter);
            this.logger.logEvent(session, "Edit", "Supplier");
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteGet(ModelAndView modelAndView, @PathVariable Long id) {
        Supplier supplier = this.supplierService.getSupplierById(id);

        modelAndView.addObject("supplier", supplier);
        modelAndView.setViewName("suppliers/delete");

        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deletePost(ModelAndView modelAndView, @PathVariable Long id, HttpSession session) {
        this.supplierService.deleteSupplier(id);
        this.logger.logEvent(session, "Delete", "Supplier");
        modelAndView.setViewName("redirect:/");

        return modelAndView;
    }
}
