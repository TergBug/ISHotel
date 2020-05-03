package org.mycode.assembler.impl;

import org.mycode.assembler.CustomerAssembler;
import org.mycode.assembler.RoomAssembler;
import org.mycode.dto.CustomerDto;
import org.mycode.model.Customer;
import org.mycode.model.Facility;
import org.mycode.model.Service;
import org.mycode.repository.FacilityRepository;
import org.mycode.repository.PaymentTypeRepository;
import org.mycode.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerAssemblerImpl implements CustomerAssembler {
    private PaymentTypeRepository paymentTypeRepository;
    private ServiceRepository serviceRepository;
    private FacilityRepository facilityRepository;
    private RoomAssembler roomAssembler;

    @Autowired
    public CustomerAssemblerImpl(PaymentTypeRepository paymentTypeRepository, ServiceRepository serviceRepository,
                                 FacilityRepository facilityRepository, RoomAssembler roomAssembler) {
        this.paymentTypeRepository = paymentTypeRepository;
        this.serviceRepository = serviceRepository;
        this.facilityRepository = facilityRepository;
        this.roomAssembler = roomAssembler;
    }

    @Override
    public Customer assemble(CustomerDto dto) {
        return new Customer(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getPassport(), dto.getStartDate(),
                dto.getEndDate(), dto.getFees(), paymentTypeRepository.findPaymentTypeByName(dto.getPaymentType()),
                roomAssembler.assemble(dto.getRoom()),
                dto.getServices().stream().map(el -> serviceRepository.findServiceByName(el))
                        .collect(Collectors.toList()),
                dto.getFacilities().keySet().stream().map(el -> facilityRepository.findFacilityByName(el))
                        .collect(Collectors.toList()));
    }

    @Override
    public CustomerDto assemble(Customer model) {
        return new CustomerDto(model.getId(), model.getFirstName(), model.getLastName(), model.getPassport(),
                model.getStartDate(), model.getEndDate(), model.getFees(), model.getPaymentType().getName(),
                roomAssembler.assemble(model.getRoom()),
                model.getServices().stream().map(Service::getName)
                        .collect(Collectors.toList()),
                model.getFacilities().stream()
                        .collect(Collectors.toMap(Facility::getName, Facility::getQuantity)));
    }
}
