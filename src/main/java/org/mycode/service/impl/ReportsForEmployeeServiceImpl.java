package org.mycode.service.impl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.mycode.assembler.RoomTypeAssembler;
import org.mycode.dto.*;
import org.mycode.repository.RoomTypeRepository;
import org.mycode.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.StreamSupport;

@Service
public class ReportsForEmployeeServiceImpl implements ReportsForEmployeeService {
    private ReportMakerService reportMakerService;
    private CustomerService customerService;
    private FacilityService facilityService;
    private RoomService roomService;
    private RoomTypeRepository roomTypeRepository;
    private RoomTypeAssembler roomTypeAssembler;

    @Autowired
    public ReportsForEmployeeServiceImpl(ReportMakerService reportMakerService, CustomerService customerService,
                                         FacilityService facilityService, RoomService roomService,
                                         RoomTypeRepository roomTypeRepository, RoomTypeAssembler roomTypeAssembler) {
        this.reportMakerService = reportMakerService;
        this.customerService = customerService;
        this.facilityService = facilityService;
        this.roomService = roomService;
        this.roomTypeRepository = roomTypeRepository;
        this.roomTypeAssembler = roomTypeAssembler;
    }

    @Override
    public XWPFDocument freeRooms() {
        Map<RoomTypeDto, Long> roomTypes = new HashMap<>();
        List<RoomDto> rooms = roomService.getAll();
        StreamSupport.stream(roomTypeRepository.findAll().spliterator(), false)
                .map(el -> roomTypeAssembler.assemble(el)).forEach(el -> roomTypes.put(el, rooms.stream()
                .filter(eln -> eln.getState().equals("FREE") && eln.getRoomType().equals(el)).count()));

        List<DocumentTableRow> rows = new ArrayList<>();
        rows.add(new DocumentTableRow(Arrays.asList("Type", "Persons", "Quantity")));
        roomTypes.keySet().forEach(el -> rows.add(new DocumentTableRow(Arrays.asList(el.getName(),
                String.valueOf(el.getPersons()), String.valueOf(roomTypes.get(el))))));
        return reportMakerService.generateReport(rows);
    }

    @Override
    public XWPFDocument customers() {
        List<CustomerDto> customers = customerService.getAll();
        List<DocumentTableRow> rows = new ArrayList<>();
        rows.add(new DocumentTableRow(Arrays.asList("First name", "Last name", "Room", "To pay")));
        customers.forEach(el -> rows.add(new DocumentTableRow(Arrays.asList(el.getFirstName(), el.getLastName(),
                el.getRoom().getRoomType().getName().toUpperCase() + " for "
                        + el.getRoom().getRoomType().getPersons()
                        + " persons on " + el.getRoom().getFloor() + " floor",
                el.getTotalPayment().toString()))));
        return reportMakerService.generateReport(rows);
    }

    @Override
    public XWPFDocument facilities() {
        List<FacilityDto> facilities = facilityService.getAll();
        List<DocumentTableRow> rows = new ArrayList<>();
        rows.add(new DocumentTableRow(Arrays.asList("Type", "Quantity in storage")));
        facilities.forEach(el -> rows.add(new DocumentTableRow(Arrays.asList(el.getName(),
                String.valueOf(el.getQuantity())))));
        return reportMakerService.generateReport(rows);
    }
}
