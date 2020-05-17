package org.mycode.controller;

import org.mycode.assembler.SimpleCustomerConverter;
import org.mycode.dto.*;
import org.mycode.service.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("order")
public class OrderController {
    private CustomerService customerService;
    private RoomService roomService;
    private ServiceService serviceService;
    private FacilityService facilityService;
    private PaymentService paymentService;
    private AuthorityService authorityService;
    private OrderRoomService orderRoomService;
    private OrderServiceService orderServiceService;
    private OrderFacilityService orderFacilityService;
    private SimpleCustomerConverter simpleCustomerConverter;
    private TotalPaymentReportService totalPaymentReportService;

    public OrderController(CustomerService customerService, RoomService roomService, ServiceService serviceService,
                           FacilityService facilityService, PaymentService paymentService,
                           AuthorityService authorityService, OrderRoomService orderRoomService,
                           OrderServiceService orderServiceService, OrderFacilityService orderFacilityService,
                           SimpleCustomerConverter simpleCustomerConverter, TotalPaymentReportService totalPaymentReportService) {
        this.customerService = customerService;
        this.roomService = roomService;
        this.serviceService = serviceService;
        this.facilityService = facilityService;
        this.paymentService = paymentService;
        this.authorityService = authorityService;
        this.orderRoomService = orderRoomService;
        this.orderServiceService = orderServiceService;
        this.orderFacilityService = orderFacilityService;
        this.simpleCustomerConverter = simpleCustomerConverter;
        this.totalPaymentReportService = totalPaymentReportService;
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
        return "orders/orderRoom";
    }

    @PostMapping("room")
    public String orderRoom(Model model, @ModelAttribute("customer") SimpleCustomerDto simpleCustomerDto,
                            HttpServletRequest request) {
        if (simpleCustomerDto.getId() == 0L) {
            model.addAttribute("customer", new SimpleCustomerDto());
            model.addAttribute("customerId", orderRoomService
                    .order(simpleCustomerConverter.convert(simpleCustomerDto)));
            model.addAttribute("roomId", simpleCustomerDto.getRoomId());
            model.addAttribute("paymentTypes", paymentService.getAllPaymentTypes());
            return "orders/customerInfo";
        }
        orderRoomService.order(simpleCustomerConverter.convert(simpleCustomerDto));
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.PERMANENT_REDIRECT);
        model.addAttribute("info", new TempInfo(simpleCustomerDto.getPassport()));
        return "redirect:/csignup";
    }

    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public String order() {
        return "orders/order";
    }

    @GetMapping("service")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String orderService(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        List<String> customerServices = customerService.getByPassport(customerPassport).getServices();
        model.addAttribute("services", serviceService.getAll().stream()
                .filter(el -> !customerServices.contains(el.getName())).collect(Collectors.toList()));
        model.addAttribute("selectedService", new ServiceDto());
        return "orders/orderService";
    }

    @PostMapping("service")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String orderService(@ModelAttribute("selectedService") ServiceDto selectedService) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        orderServiceService.order(customerPassport, selectedService);
        return "redirect:/";
    }

    @GetMapping("service/review")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String serviceReview(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        model.addAttribute("services", customerService.getByPassport(customerPassport).getServices());
        model.addAttribute("selectedService", new ServiceDto());
        return "orders/serviceReview";
    }

    @PostMapping("service/review")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String serviceReview(@ModelAttribute("selectedService") ServiceDto selectedService) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        orderServiceService.denyOrder(customerPassport, selectedService);
        return "redirect:/";
    }

    @GetMapping({"facility"})
    @PreAuthorize("hasRole('CUSTOMER')")
    public String orderFacility(Model model) {
        model.addAttribute("facilities", facilityService.getAll());
        model.addAttribute("selectedFacility", new FacilityDto());
        return "orders/orderFacility";
    }

    @PostMapping("facility")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String orderFacility(@ModelAttribute("selectedFacility") FacilityDto selectedFacility) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        orderFacilityService.order(customerPassport, selectedFacility);
        return "redirect:/";
    }

    @GetMapping("facility/review")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String facilityReview(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        model.addAttribute("facilities", customerService.getByPassport(customerPassport).getFacilities());
        model.addAttribute("selectedFacility", new FacilityDto());
        return "orders/facilityReview";
    }

    @PostMapping("facility/review")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String facilityReview(@ModelAttribute("selectedFacility") FacilityDto selectedFacility) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        orderFacilityService.denyOrder(customerPassport, selectedFacility);
        return "redirect:/";
    }

    @GetMapping("report")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<byte[]> report() throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String customerPassport = authorityService.getIdentityInfoOfUser(username);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        totalPaymentReportService.report(customerPassport).write(baos);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/msword"));
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }
}
