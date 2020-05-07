package org.mycode.controller;

import org.mycode.assembler.SimpleCustomerConverter;
import org.mycode.dto.RoomTypeDto;
import org.mycode.dto.SimpleCustomerDto;
import org.mycode.service.OrderRoomService;
import org.mycode.service.PaymentService;
import org.mycode.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("order")
public class OrderController {
    private OrderRoomService orderRoomService;
    private RoomService roomService;
    private SimpleCustomerConverter simpleCustomerConverter;
    private PaymentService paymentService;

    @Autowired
    public OrderController(OrderRoomService orderRoomService, RoomService roomService,
                           SimpleCustomerConverter simpleCustomerConverter, PaymentService paymentService) {
        this.orderRoomService = orderRoomService;
        this.roomService = roomService;
        this.simpleCustomerConverter = simpleCustomerConverter;
        this.paymentService = paymentService;
    }

    @GetMapping({"room", "room/{roomType}", "room/{roomType}/{persons}"})
    public String orderRoom(Model model, @PathVariable(required = false) String roomType,
                            @PathVariable(required = false) Integer persons) {
        model.addAttribute("customer", new SimpleCustomerDto());
        model.addAttribute("roomTypes", roomService.getAllRoomTypes());
        model.addAttribute("sRoomType", roomType == null ? "" : roomType);
        model.addAttribute("sPersons", persons == null ? 0 : persons);
        model.addAttribute("rooms", (persons == null || roomType == null) ? Collections.emptyList() :
                roomService.getFreeRoomsByType(new RoomTypeDto(roomType, persons)));
        return "orderRoom";
    }

    @PostMapping("room")
    public String orderRoom(Model model, @ModelAttribute("customer") SimpleCustomerDto simpleCustomerDto) {
        if (simpleCustomerDto.getId() == 0L) {
            model.addAttribute("customer", new SimpleCustomerDto());
            model.addAttribute("customerId", orderRoomService
                    .order(simpleCustomerConverter.convert(simpleCustomerDto)));
            model.addAttribute("roomId", simpleCustomerDto.getRoomId());
            model.addAttribute("paymentTypes", paymentService.getAllPaymentTypes());
            return "customerInfo";
        }
        orderRoomService.order(simpleCustomerConverter.convert(simpleCustomerDto));
        return "redirect:/";
    }
}
