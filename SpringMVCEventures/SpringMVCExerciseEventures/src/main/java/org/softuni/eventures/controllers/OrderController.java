package org.softuni.eventures.controllers;

import org.softuni.eventures.models.binding.OrderMessageBindingModel;
import org.softuni.eventures.models.view.OrdersAllViewModel;
import org.softuni.eventures.services.MessageService;
import org.softuni.eventures.services.ModelService;
import org.softuni.eventures.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final MessageService messageService;
    private final OrderService orderService;
    private final ModelService modelService;

    @Autowired
    public OrderController(MessageService messageService, OrderService orderService, ModelService modelService) {
        this.messageService = messageService;
        this.orderService = orderService;
        this.modelService = modelService;
    }

    @PostMapping("/send")
    public String sendPost(@ModelAttribute OrderMessageBindingModel orderModel) {
        this.messageService.sendMessage(orderModel);

        return "redirect:/events/all";
    }

    @GetMapping("/all")
    public String allGet(Model model) {
        List<OrdersAllViewModel> orderList = this.modelService.mapOrderListToModelList(this.orderService.getAll());
        model.addAttribute("orderList", orderList);

        return "orders/all";
    }
}
