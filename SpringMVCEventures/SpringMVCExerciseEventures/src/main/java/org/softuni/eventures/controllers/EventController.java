package org.softuni.eventures.controllers;

import org.softuni.eventures.entities.Event;
import org.softuni.eventures.models.binding.CreateEventBindingModel;
import org.softuni.eventures.models.view.EventsMineViewModel;
import org.softuni.eventures.models.view.OrdersAllViewModel;
import org.softuni.eventures.services.EventService;
import org.softuni.eventures.services.ModelService;
import org.softuni.eventures.validators.CreateEventValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final ModelService modelService;
    private final CreateEventValidator validator;

    @Autowired
    public EventController(EventService eventService, ModelService modelService,
                           CreateEventValidator validator) {
        this.eventService = eventService;
        this.modelService = modelService;
        this.validator = validator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.validator);
    }

    @GetMapping("/test")
    public String test( OrdersAllViewModel model) {
        System.out.println(model.getCustomerName());
        return "index";
    }

    @GetMapping("/all")
    public String getAll(Model model, @PageableDefault(size = 12) Pageable pageable) {
        Page<Event> eventPage = this.eventService.getPage(pageable);
        model.addAttribute("eventsPage", this.modelService.mapEventPageToEventsAllViewModelPage(eventPage));
        return "events/all";
    }

    @GetMapping("/mine")
    public String mineAll(Model model, Principal principal) {
        List<EventsMineViewModel> eventsList = this.eventService.getEventsMineViewModelListForUser(principal.getName());
        model.addAttribute("eventsList", eventsList);

        return "events/mine";
    }

    @GetMapping("/create")
    public String createGet(@ModelAttribute("model") CreateEventBindingModel bindingModel) {
        return "events/create";
    }

    @PostMapping("/create")
    public String createPost(@Valid @ModelAttribute("model") CreateEventBindingModel bindingModel,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(BindingResult.MODEL_KEY_PREFIX + "model", bindingResult);
            return "events/create";
        } else {
            Event event = this.modelService.mapModelToEvent(bindingModel);
            this.eventService.saveEvent(event);
            return "events/all";
        }
    }
}
