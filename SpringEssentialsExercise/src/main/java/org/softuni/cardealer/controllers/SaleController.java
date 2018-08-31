package org.softuni.cardealer.controllers;

import org.softuni.cardealer.entities.Sale;
import org.softuni.cardealer.models.view.SaleViewModel;
import org.softuni.cardealer.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ModelAndView all(ModelAndView modelAndView) {
        List<Sale> saleList = this.saleService.getAllSales();

        modelAndView.addObject("sales", saleList);
        modelAndView.setViewName("sales/all");

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView saleDetails(@PathVariable Long id, ModelAndView modelAndView) {
        SaleViewModel sale = this.saleService.getSaleViewModelById(id);

        modelAndView.addObject("sale", sale);
        modelAndView.setViewName("sales/details");

        return modelAndView;
    }

    @GetMapping("/discounted")
    public ModelAndView salesDiscounted(ModelAndView modelAndView) {
        List<SaleViewModel> saleList = this.saleService.getSaleViewModelWithDiscount();

        modelAndView.addObject("sales", saleList);
        modelAndView.setViewName("sales/discounted");

        return modelAndView;
    }

    @GetMapping("/discounted/{percentage}")
    public ModelAndView salesWithConcreteDiscount(@PathVariable int percentage, ModelAndView modelAndView) {
        List<SaleViewModel> saleList = this.saleService.getSaleViewModelWithDiscount(percentage);

        modelAndView.addObject("sales", saleList);
        modelAndView.setViewName("sales/discounted");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addGet(HttpSession session, ModelAndView modelAndView) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
        } else {
            modelAndView.setViewName("sales/add");
        }

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@RequestParam String customerName,
                                @RequestParam String carMake,
                                @RequestParam String carModel,
                                @RequestParam String discountPercentage,
                                RedirectAttributes redirectAttributes,
                                HttpSession session,
                                ModelAndView modelAndView) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
        } else {
            String exceptionMessage = this.saleService.validateSale(customerName, carMake, carModel);

            if (exceptionMessage != null) {
                modelAndView.addObject("exceptionMessage", exceptionMessage);
                modelAndView.setViewName("sales/add");
            } else {
                SaleViewModel sale = this.saleService.createSaleViewModel(
                        customerName, carMake, carModel, discountPercentage);
                redirectAttributes.addFlashAttribute("sale", sale);
                modelAndView.setViewName("redirect:/sales/review");
            }
        }

        return modelAndView;
    }

    @GetMapping("/review")
    public ModelAndView reviewGet(ModelAndView modelAndView) {
        modelAndView.setViewName("sales/review");
        return modelAndView;
    }

    @PostMapping("/review")
    public ModelAndView reviewPost(SaleViewModel saleViewModel,
                                HttpSession session,
                                ModelAndView modelAndView) {
        if (session.getAttribute("username") == null) {
            modelAndView.setViewName("permission");
        } else {
            this.saleService.persistSale(saleViewModel);
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }
}
