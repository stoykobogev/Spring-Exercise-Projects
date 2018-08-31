package org.softuni.residentevil.controllers;

import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.binding.VirusBindingModel;
import org.softuni.residentevil.services.CapitalService;
import org.softuni.residentevil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/viruses")
public class VirusController {

    private final VirusService virusService;
    private final CapitalService capitalService;

    @Autowired
    public VirusController(VirusService virusService, CapitalService capitalService) {
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<Virus> virusList = this.virusService.getAll();
        modelAndView.addObject("viruses", virusList);
        modelAndView.setViewName("viruses/all");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addGet(@ModelAttribute VirusBindingModel virusBindingModel,
                               ModelAndView modelAndView, Model model) {
        if (!model.containsAttribute("model")) {
            modelAndView.addObject("model", virusBindingModel);
        }
        modelAndView.setViewName("viruses/add");
        List<Capital> capitals = this.capitalService.getAll();
        modelAndView.addObject("capitals", capitals);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPost(@Valid @ModelAttribute VirusBindingModel virusBindingModel,
                                BindingResult bindingResult,
                                ModelAndView modelAndView,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            redirectAttributes.addFlashAttribute("model", virusBindingModel);
            modelAndView.setViewName("redirect:/viruses/add");
        } else {
            this.virusService.persistVirus(virusBindingModel);
            modelAndView.setViewName("redirect:/viruses/all");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editGet(ModelAndView modelAndView, @PathVariable Long id) {
        VirusBindingModel virus = this.virusService.getVirusModelById(id);
        modelAndView.addObject("model", virus);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("viruses/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editPost(ModelAndView modelAndView, @PathVariable Long id,
                                 @Valid @ModelAttribute VirusBindingModel virusBindingModel,
                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("org.springframework.validation.BindingResult.model", bindingResult);
            modelAndView.addObject("model", virusBindingModel);
            modelAndView.setViewName("viruses/edit");
        } else {
            this.virusService.updateVirus(id, virusBindingModel);
            modelAndView.setViewName("redirect:/viruses/all");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGet(ModelAndView modelAndView, @PathVariable Long id) {
        VirusBindingModel virus = this.virusService.getVirusModelById(id);
        modelAndView.addObject("model", virus);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("viruses/delete");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView deletePost(ModelAndView modelAndView, @PathVariable Long id) {

        this.virusService.deleteVirus(id);
        modelAndView.setViewName("redirect:/viruses/all");

        return modelAndView;
    }
}
